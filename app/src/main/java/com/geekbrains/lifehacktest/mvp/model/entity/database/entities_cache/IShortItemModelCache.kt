package com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache

import com.geekbrains.lifehacktest.mvp.model.entity.ShortItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl.Database
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.RoomShortItemModel

interface IShortItemModelCache {
    fun saveShortItemToDb(shortItem: ShortItemModel, database: Database)
    fun getShortItemFromDb(shortItemId: String, database: Database): RoomShortItemModel?
}