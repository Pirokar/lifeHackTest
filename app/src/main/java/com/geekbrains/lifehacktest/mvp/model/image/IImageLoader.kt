package com.geekbrains.lifehacktest.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}