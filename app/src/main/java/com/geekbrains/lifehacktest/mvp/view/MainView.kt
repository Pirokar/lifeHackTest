package com.geekbrains.lifehacktest.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun initList()
    fun updateList()
    fun initSwipeToRefresh()
    fun showNoItems()
    fun hideNoItems()
    fun hideProgressBars()
    fun showDetailsScreen(id: String)
}