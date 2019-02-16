package com.example.imgurgallery.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imgurgallery.api.GalleryItem
import com.example.imgurgallery.api.Resource
import com.example.imgurgallery.applySchedulers
import com.example.imgurgallery.smartSubscribe
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val mutableLiveData: MutableLiveData<Resource<List<GalleryItem>>> = MutableLiveData()

    fun getGallery() {
        compositeDisposable.add(
            mainRepository.getGallery()
                .applySchedulers()
                .smartSubscribe(
                    onStart = { mutableLiveData.value = Resource.loading() },
                    onSuccess = { mutableLiveData.value = Resource.success(it) },
                    onError = { mutableLiveData.value = Resource.error(it) },
                    onFinish = { mutableLiveData.value = Resource.finished() }
                )
        )
    }

    fun response() = mutableLiveData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}