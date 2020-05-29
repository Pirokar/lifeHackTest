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
    version = 1
)
abstract class Database: RoomDatabase() {
    abstract var shortItemDao: ShortItemDao
    abstract var detailedItemDao: DetailedItemDao

    companion object {
        private const val DB_NAME = "database.db"

        @Volatile
        private var instance: Database? = null

        @Synchronized
        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context) {
            instance ?: let {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
            }
        }

    }
}