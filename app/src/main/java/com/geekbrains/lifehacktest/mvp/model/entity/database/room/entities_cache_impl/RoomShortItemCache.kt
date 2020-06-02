package com.geekbrains.lifehacktest.mvp.model.entity.database.room.entities_cache_impl

import com.geekbrains.lifehacktest.mvp.model.entity.DetailedItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.ShortItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl.Database
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IShortItemModelCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.RoomShortItemModel

class RoomShortItemCache(private val database: Database): IShortItemModelCache {
    override fun saveShortItemToDb(shortItem: ShortItemModel) {
        val roomShortItemModel = database.shortItemDao().getById(shortItem.id)?.apply {
            id = shortItem.id
            name = shortItem.name
            img = shortItem.img
        } ?: RoomShortItemModel(shortItem.id, shortItem.name, shortItem.img)
        database.shortItemDao().insert(roomShortItemModel)
    }

    override fun saveShortItemsToDb(shortItems: Array<ShortItemModel>) {
        val roomModels = shortItems.map {
            RoomShortItemModel(
                it.id,
                it.name,
                it.img
            )
        }
        database.shortItemDao().insert(roomModels)
    }

    override fun getShortItemFromDb(shortItemId: String): ShortItemModel? {
        return  database.shortItemDao().getById(shortItemId)?.mapToSimpleModel()
    }

    override fun getAllItems(): Array<ShortItemModel> {
        return database.shortItemDao().getAll().map {
            val shortItemModel = ShortItemModel()
            shortItemModel.id = it.id
            shortItemModel.name = it.name
            shortItemModel.img = it.img

            shortItemModel
        }.toTypedArray()
    }
}