package com.geekbrains.lifehacktest.mvp.model.repo

import com.geekbrains.lifehacktest.mvp.model.api.IDataSource
import com.geekbrains.lifehacktest.mvp.model.entity.DetailedItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.ShortItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IDetailedItemModelCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IShortItemModelCache
import com.geekbrains.lifehacktest.mvp.model.network.NetworkStatus
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ModelsRepo {

    @Inject lateinit var api: IDataSource
    @Inject lateinit var networkStatus: NetworkStatus
    @Inject lateinit var shortItemCache: IShortItemModelCache
    @Inject lateinit var detailedItemCache: IDetailedItemModelCache

    fun getItems(): @NonNull Single<Array<ShortItemModel>>
            = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if(isOnline) {
            api.getItemsList()
                .map { itemsList ->
                    shortItemCache.saveShortItemsToDb(itemsList)
                    itemsList
                }
        } else {
            Single.create { emitter ->
                val items = shortItemCache.getAllItems()
                emitter.onSuccess(items)
            }
        }
    }.subscribeOn(Schedulers.io())

    fun getDetailedItem(id: String): @NonNull Single<DetailedItemModel>
            = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if(isOnline) {
            api.getMainItemModel(id)
                .map { detailedItems ->
                    detailedItemCache
                        .saveDetailedItemToDb(detailedItems[0])
                    detailedItems[0]
                }
        } else {
            Single.create { emitter ->
                val item = detailedItemCache.getDetailedItemsFromDb(id)
                emitter.onSuccess(item)
            }
        }
    }.subscribeOn(Schedulers.io())
}