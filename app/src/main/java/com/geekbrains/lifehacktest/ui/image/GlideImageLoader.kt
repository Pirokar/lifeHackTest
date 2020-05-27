package com.geekbrains.lifehacktest.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.geekbrains.lifehacktest.R
import com.geekbrains.lifehacktest.mvp.model.image.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .placeholder(R.drawable.no_image_placeholder)
            .load(url)
            .into(container)
    }
}