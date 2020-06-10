package com.geekbrains.lifehacktest.framework.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.geekbrains.lifehacktest.R
import com.geekbrains.lifehacktest.mvp.model.image.IImageLoader
import javax.inject.Inject

class GlideImageLoader  @Inject constructor() : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .placeholder(R.drawable.no_image_placeholder)
            .load(url)
            .into(container)
    }
}