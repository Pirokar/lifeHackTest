package com.geekbrains.lifehacktest.mvp.model.entity.database.room.entities_cache_impl

import com.geekbrains.lifehacktest.mvp.model.entity.ShortItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl.Database
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IShortItemModelCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.RoomShortItemModel

class RoomShortItemCache: IShortItemModelCache {
    override fun saveShortItemToDb(shortItem: ShortItemModel, database: Database) {
        val roomShortItemModel = database.shortItemDao.getById(shortItem.id)?.apply {
            id = shortItem.id
            name = shortItem.name
            img = shortItem.img
        } ?: RoomShortItemModel(shortItem.id, shortItem.name, shortItem.img)
        database.shortItemDao.insert(roomShortItemModel)
    }

    override fun getShortItemFromDb(shortItemId: String, database: Database): RoomShortItemModel? {
        return database.shortItemDao.getById(shortItemId)
    }
}