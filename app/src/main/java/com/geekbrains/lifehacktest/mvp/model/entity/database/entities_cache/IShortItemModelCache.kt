package com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache

import com.geekbrains.lifehacktest.mvp.model.entity.ShortItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl.Database

interface IShortItemModelCache {
    fun saveShortItemToDb(shortItem: ShortItemModel)
    fun saveShortItemsToDb(shortItems: Array<ShortItemModel>)
    fun getShortItemFromDb(shortItemId: String): ShortItemModel?
    fun getAllItems(): Array<ShortItemModel>
}