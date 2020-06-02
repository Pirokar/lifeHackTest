package com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.RoomDetailedItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.RoomShortItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.dao.DetailedItemDao
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.dao.ShortItemDao
import java.lang.RuntimeException

@androidx.room.Database(
    entities = [
        RoomShortItemModel::class,
        RoomDetailedItemModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database: RoomDatabase() {
    abstract fun shortItemDao(): ShortItemDao
    abstract fun detailedItemDao(): DetailedItemDao

    companion object {
        const val DB_NAME = "database.db"
    }
}