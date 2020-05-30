package com.geekbrains.lifehacktest.mvp.model.repo

import com.geekbrains.App
import com.geekbrains.lifehacktest.mvp.model.api.IDataSource
import com.geekbrains.lifehacktest.mvp.model.entity.DetailedItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.ShortItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl.Database
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IDetailedItemModelCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IShortItemModelCache
import com.geekbrains.lifehacktest.mvp.model.network.NetworkStatus
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ModelsRepo(private val api: IDataSource) {
    fun getItems(): @NonNull Single<Array<ShortItemModel>>
            = App.getNetworkStatusImpl().isOnlineSingle().flatMap { isOnline ->
        if(isOnline) {
            api.getItemsList()
                .map { itemsList ->
                    App.getShortItemCacheImpl().saveShortItemsToDb(itemsList, App.getDBImpl())
                    itemsList
                }
        } else {
            Single.create { emitter ->
                val items = App.getShortItemCacheImpl().getAllItems(App.getDBImpl())
                emitter.onSuccess(items)
            }
        }
    }.subscribeOn(Schedulers.io())

    /*fun getDetaileddItem(id: String): @NonNull Single<DetailedItemModel> {

    }*/

    fun getDetailedItem(id: String): @NonNull Single<DetailedItemModel>
            = App.getNetworkStatusImpl().isOnlineSingle().flatMap { isOnline ->
        if(isOnline) {
            api.getMainItemModel(id)
                .map { detailedItems ->
                    App.getDetailedItemCacheImpl()
                        .saveDetailedItemToDb(detailedItems[0], App.getDBImpl())
                    detailedItems[0]
                }
        } else {
            Single.create { emitter ->
                val item = App.getDetailedItemCacheImpl().getDetailedItemsFromDb(id, App.getDBImpl())
                emitter.onSuccess(item)
            }
        }
    }.subscribeOn(Schedulers.io())
}