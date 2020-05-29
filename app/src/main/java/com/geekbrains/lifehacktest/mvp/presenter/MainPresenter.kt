package com.geekbrains.lifehacktest.mvp.presenter

import com.geekbrains.lifehacktest.mvp.model.api.ApiHolder
import com.geekbrains.lifehacktest.mvp.model.entity.ShortItemModel
import com.geekbrains.lifehacktest.mvp.model.repo.ModelsRepo
import com.geekbrains.lifehacktest.mvp.presenter.list.IItemsListPresenter
import com.geekbrains.lifehacktest.mvp.view.MainView
import com.geekbrains.lifehacktest.mvp.view.list.ShortItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import timber.log.Timber

class MainPresenter(private val mainThreadScheduler: Scheduler): MvpPresenter<MainView>() {
    private lateinit var modelsRepo: ModelsRepo
    val itemsListPresenter = ItemsListPresenter()

    class ItemsListPresenter : IItemsListPresenter {
        var items = Array(0) { ShortItemModel() }
        override var itemClickListener: ((ShortItemView) -> Unit)? = null

        override fun getCount() = items.size

        override fun bindView(view: ShortItemView) {
            val item = items[view.pos]
            view.setTitle(item.name)
            view.setImage(item.img)
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList()
        viewState.initSwipeToRefresh()
        createModelsRepo()
        loadItems()
        setOnItemClickImpl()
    }

    fun onSwipeRefreshDragged() {
        loadItems()
    }

    private fun createModelsRepo() {
        modelsRepo = ModelsRepo(ApiHolder.api)
    }

    private fun loadItems() {
        modelsRepo.getItems()
            .observeOn(mainThreadScheduler)
            .subscribe({items ->
                itemsListPresenter.items = items
                viewState.hideProgressBars()
                viewState.updateList()
            },{
                Timber.e(it)
            })
    }

    private fun setOnItemClickImpl() {
        itemsListPresenter.itemClickListener = {shortItem ->
            viewState.showDetailsScreen(itemsListPresenter.items[shortItem.pos].id)
        }
    }
}