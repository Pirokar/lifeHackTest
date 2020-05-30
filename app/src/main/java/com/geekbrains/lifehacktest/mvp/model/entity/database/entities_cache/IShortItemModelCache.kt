package com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache

import com.geekbrains.lifehacktest.mvp.model.entity.ShortItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl.Database

interface IShortItemModelCache {
    fun saveShortItemToDb(shortItem: ShortItemModel, database: Database)
    fun saveShortItemsToDb(shortItems: Array<ShortItemModel>, database: Database)
    fun getShortItemFromDb(shortItemId: String, database: Database): ShortItemModel?
    fun getAllItems(database: Database): Array<ShortItemModel>
}