package com.geekbrains.lifehacktest.mvp.presenter

import com.geekbrains.lifehacktest.mvp.model.DetailedModel
import com.geekbrains.lifehacktest.mvp.model.IIdProvider
import com.geekbrains.lifehacktest.mvp.model.api.ApiHolder
import com.geekbrains.lifehacktest.mvp.model.repo.ModelsRepo
import com.geekbrains.lifehacktest.mvp.view.DetailedView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import timber.log.Timber

class DetailedPresenter(private val idProvider: IIdProvider,
                        private val mainThreadScheduler: Scheduler): MvpPresenter<DetailedView>() {
    private val model = DetailedModel()
    private lateinit var modelsRepo: ModelsRepo

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setupFindUsBtn()
        viewState.setupPhoneView()
        viewState.setupWWWView()

        createModelsRepo()
        loadDetails()
    }

    private fun createModelsRepo() {
        modelsRepo = ModelsRepo(ApiHolder.api)
    }

    private fun loadDetails() {
        val id = idProvider.getId() ?: ""
        modelsRepo.getDetailedItem(id)
            .observeOn(mainThreadScheduler)
            .subscribe({items ->
                if(items.isNotEmpty()) {
                    model.detailedModel = items[0]
                    viewState.hideErrorLoading()
                    viewState.hideLoaders()
                    viewState.showContentView()

                    setName()
                    setImage()
                    setDescription()
                    setFindUspBtn()
                    setWWW()
                    setPhone()
                } else {
                    viewState.showErrorLoading()
                }
            },{
                Timber.e(it)
                viewState.showErrorLoading()
                viewState.hideLoaders()
            })
    }

    fun onFindUsBtnClicked() {
        viewState.showPositionOnMap(model.detailedModel.latitude, model.detailedModel.longitude)
    }

    fun onWWWClicked() {
        viewState.showUrlInBrowser(model.detailedModel.www)
    }

    fun onPhoneClicked() {
        viewState.openPhoneDialer(model.detailedModel.phone)
    }

    private fun setName() {
        viewState.setName(model.detailedModel.name)
    }

    private fun setImage() {
        viewState.setImage(model.detailedModel.img)
    }

    private fun setDescription() {
        viewState.setDescription(model.detailedModel.description)
    }

    private fun setFindUspBtn() {
        if(model.detailedModel.latitude != 0f && model.detailedModel.longitude != 0f) {
            viewState.setupFindUsBtn()
        } else {
            viewState.hideFindUsBtn()
        }
    }

    private fun setWWW() {
        if(model.detailedModel.www.isNotBlank()) {
            viewState.setWWW(model.detailedModel.www)
        } else {
            viewState.hideWWW()
        }
    }

    private fun setPhone() {
        if(model.detailedModel.phone.isNotBlank()) {
            viewState.setPhoneNmb(model.detailedModel.phone)
        } else {
            viewState.hidePhoneNmb()
        }
    }
}