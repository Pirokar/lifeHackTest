package com.geekbrains.lifehacktest.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailedView: MvpView {
    fun showContentView()
    fun setImage(url: String)
    fun setName(text: String)
    fun setDescription(text: String)
    fun setupToolbar()
    fun setupFindUsBtn()
    fun hideFindUsBtn()
    fun showPositionOnMap(latitude: Float, longitude: Float)
    fun setupWWWView()
    fun setWWW(urlText: String)
    fun hideWWW()
    fun showUrlInBrowser(urlText: String)
    fun setupPhoneView()
    fun setPhoneNmb(text: String)
    fun openPhoneDialer(phoneNmb: String)
    fun hidePhoneNmb()
    fun showErrorLoading()
    fun hideErrorLoading()
    fun hideLoaders()
    fun goBack()
}