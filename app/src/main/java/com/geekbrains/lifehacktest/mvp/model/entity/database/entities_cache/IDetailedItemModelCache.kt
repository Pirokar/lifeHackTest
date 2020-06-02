package com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache

import com.geekbrains.lifehacktest.mvp.model.entity.DetailedItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl.Database

interface IDetailedItemModelCache {
    fun saveDetailedItemToDb(detailedItem: DetailedItemModel)
    fun getDetailedItemsFromDb(detailedItemId: String): DetailedItemModel?
}