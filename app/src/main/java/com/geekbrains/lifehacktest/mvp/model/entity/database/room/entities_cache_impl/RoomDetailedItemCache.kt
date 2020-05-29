package com.geekbrains.lifehacktest.mvp.model.entity.database.room.entities_cache_impl

import com.geekbrains.lifehacktest.mvp.model.entity.DetailedItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl.Database
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IDetailedItemModelCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.RoomDetailedItemModel

class RoomDetailedItemCache: IDetailedItemModelCache {
    override fun saveShortItemToDb(detailedItem: DetailedItemModel, database: Database) {
        val itemToInsert = RoomDetailedItemModel(
            detailedItem.id,
            detailedItem.name,
            detailedItem.img,
            detailedItem.description,
            detailedItem.latitude,
            detailedItem.longitude,
            detailedItem.www,
            detailedItem.phone
        )
        database.detailedItemDao.insert(itemToInsert)
    }

    override fun getShortItemFromDb(detailedItemId: String, database: Database): RoomDetailedItemModel? {
        return database.detailedItemDao.getById(detailedItemId)
    }
}