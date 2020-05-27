package com.geekbrains.lifehacktest.mvp.view.list

interface ShortItemView {
    var pos: Int
    fun setTitle(text: String)
    fun setImage(url: String)
}