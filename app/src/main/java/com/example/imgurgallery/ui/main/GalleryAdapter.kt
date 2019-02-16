package com.example.imgurgallery.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.imgurgallery.api.GalleryItem
import com.example.imgurgallery.api.Image
import android.graphics.Point
import com.example.imgurgallery.R
import com.example.imgurgallery.ui.custom.SquareImageView


class GalleryAdapter(private val context: Context, private val galleryItemList: List<GalleryItem>) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private val itemWidth: Int

    init {
        val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        itemWidth = size.x / 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image,
                parent, false
            )
        )

    override fun getItemCount(): Int = galleryItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(galleryItemList[position]) {
            val desc: String?
            val url: String?
            if (images_count != null && images_count > 0) {
                val image: Image = images[0]
                desc = image.description ?: (image.title ?: "No Text Information!")
                url = image.link
            } else {
                desc = description ?: (title ?: "No Text Information!")
                url = link
            }

            holder.textView.text = desc
            Glide.with(holder.itemView.context)
                .load(url)
                .thumbnail(0.1F)
                .apply(
                    RequestOptions.overrideOf(itemWidth, itemWidth)
                        .placeholder(R.drawable.item_placeholder)
                )
                .centerCrop()
                .into(holder.squareImageView)

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView by lazy { itemView.findViewById<TextView>(R.id.text_view) }
        val squareImageView: SquareImageView by lazy { itemView.findViewById<SquareImageView>(R.id.square_image_view) }
    }
}