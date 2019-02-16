package com.example.imgurgallery.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imgurgallery.R
import com.example.imgurgallery.ui.custom.SpacesItemDecoration
import com.example.imgurgallery.api.GalleryItem
import com.example.imgurgallery.api.Resource
import com.example.imgurgallery.toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var mainViewModel: MainViewModel

    private val imageList = mutableListOf<GalleryItem>()

    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)

        recycler_view.addItemDecoration(SpacesItemDecoration(10))
        recycler_view.layoutManager = GridLayoutManager(this, 4)
        galleryAdapter = GalleryAdapter(this, imageList)
        recycler_view.adapter = galleryAdapter

        mainViewModel.response().observe(this, Observer { processResponse(it) })

        mainViewModel.getGallery()
    }

    private fun processResponse(resource: Resource<List<GalleryItem>>) {
        when (resource.result) {
            Resource.Result.LOADING -> showLoading()
            Resource.Result.SUCCESS -> {
                if (resource.success != null && resource.success) {
                    val message: String
                    if (resource.data != null && resource.data.isNotEmpty()) {
                        imageList.addAll(resource.data)
                        message = "Good Work!";
                    } else {
                        message = "No Data Found!"
                    }
                    galleryAdapter.notifyItemRangeInserted(0, imageList.size)
                    toast(message)
                } else {
                    toast("SUCCESS: " + resource.error?.error)
                }

            }
            Resource.Result.ERROR -> toast("ERROR: " + resource.throwable?.message)
            Resource.Result.FINISHED -> showLoading(false)
        }
    }

    private fun showLoading(show: Boolean = true) {
        if (show) {
            if (progress_circular.visibility != View.VISIBLE) {
                progress_circular.visibility = View.VISIBLE
            }
        } else {
            if (progress_circular.visibility == View.VISIBLE) {
                progress_circular.visibility = View.GONE
            }
        }
    }
}
