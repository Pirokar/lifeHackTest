package com.geekbrains.lifehacktest.di.modules

import androidx.room.Room
import com.geekbrains.App
import com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl.Database
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IDetailedItemModelCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IShortItemModelCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.entities_cache_impl.RoomDetailedItemCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.entities_cache_impl.RoomShortItemCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database {
        return Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun detailedItemModelCache(database: Database): IDetailedItemModelCache {
        return RoomDetailedItemCache(database)
    }

    @Singleton
    @Provides
    fun shortItemModelCache(database: Database): IShortItemModelCache {
        return RoomShortItemCache(database)
    }
}