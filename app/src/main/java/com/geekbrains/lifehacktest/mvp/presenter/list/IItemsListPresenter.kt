package com.geekbrains.lifehacktest.mvp.presenter.list

import com.geekbrains.lifehacktest.mvp.view.list.ShortItemView

interface IItemsListPresenter {
    var itemClickListener: ((ShortItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: ShortItemView)
}