package com.geekbrains.lifehacktest.mvp.presenter

import com.geekbrains.App
import com.geekbrains.lifehacktest.Constants
import com.geekbrains.lifehacktest.mvp.model.DetailedModel
import com.geekbrains.lifehacktest.mvp.model.IIdProvider
import com.geekbrains.lifehacktest.mvp.model.api.IDataSource
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IDetailedItemModelCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IShortItemModelCache
import com.geekbrains.lifehacktest.mvp.model.network.NetworkStatus
import com.geekbrains.lifehacktest.mvp.model.repo.ModelsRepo
import com.geekbrains.lifehacktest.mvp.view.DetailedView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import timber.log.Timber
import javax.inject.Inject

class DetailedPresenter(private val idProvider: IIdProvider,
                        private val mainThreadScheduler: Scheduler): MvpPresenter<DetailedView>() {
    private val model = DetailedModel()
    private lateinit var modelsRepo: ModelsRepo

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setupFindUsBtn()
        viewState.setupPhoneView()
        viewState.setupWWWView()
        viewState.setupToolbar()

        createModelsRepo()
        loadDetails()
    }

    private fun createModelsRepo() {
        modelsRepo = ModelsRepo().apply {
            App.appInstance.appComponent.inject(this)
        }
    }

    private fun loadDetails() {
        val id = idProvider.getId() ?: ""
        modelsRepo.getDetailedItem(id)
            .observeOn(mainThreadScheduler)
            .subscribe({item ->
                model.detailedModel = item
                viewState.hideErrorLoading()
                viewState.hideLoaders()
                viewState.showContentView()

                setName()
                setImage()
                setDescription()
                setFindUspBtn()
                setWWW()

                setPhone()
            },{
                Timber.e(it)
                viewState.showErrorLoading()
                viewState.hideLoaders()
            })
    }

    fun onBackClicked() {
        viewState.goBack()
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
        viewState.setImage("${Constants.imagesPrefix}${model.detailedModel.img}")
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