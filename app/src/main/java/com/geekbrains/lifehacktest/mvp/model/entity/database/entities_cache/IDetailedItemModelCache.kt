package com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache

import com.geekbrains.lifehacktest.mvp.model.entity.DetailedItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl.Database
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.RoomDetailedItemModel

interface IDetailedItemModelCache {
    fun saveShortItemToDb(detailedItem: DetailedItemModel, database: Database)
    fun getShortItemFromDb(detailedItemId: String, database: Database): RoomDetailedItemModel?
}