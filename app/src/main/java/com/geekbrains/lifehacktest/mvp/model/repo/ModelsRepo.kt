package com.geekbrains.lifehacktest.mvp.model.repo

import com.geekbrains.lifehacktest.mvp.model.api.IDataSource
import com.geekbrains.lifehacktest.mvp.model.entity.DetailedItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.ShortItemModel
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ModelsRepo(private val api: IDataSource) {
    fun getItems(): @NonNull Single<Array<ShortItemModel>>
            = api.getItemsList().subscribeOn(Schedulers.io())

    fun getDetailedItem(id: String): @NonNull Single<Array<DetailedItemModel>>
            = api.getMainItemModel(id).subscribeOn(Schedulers.io())
}