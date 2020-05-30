package com.geekbrains

import android.app.Application
import android.content.Context
import com.geekbrains.lifehacktest.framework.network.AndroidNetworkStatus
import com.geekbrains.lifehacktest.mvp.model.entity.database.db_impl.Database
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IDetailedItemModelCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.entities_cache.IShortItemModelCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.entities_cache_impl.RoomDetailedItemCache
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.entities_cache_impl.RoomShortItemCache
import com.geekbrains.lifehacktest.mvp.model.network.NetworkStatus

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        Database.create(this)
    }

    companion object {
        lateinit var context: Context
        private var networkStatusInstance: NetworkStatus? = null

        fun getShortItemCacheImpl(): IShortItemModelCache {
            return RoomShortItemCache
        }

        fun getDetailedItemCacheImpl(): IDetailedItemModelCache {
            return RoomDetailedItemCache
        }

        fun getDBImpl(): Database {
            return Database.getInstance()
        }

        fun getNetworkStatusImpl(): NetworkStatus {
            if(networkStatusInstance == null) {
                networkStatusInstance = AndroidNetworkStatus(context)
            }

            return networkStatusInstance!!
        }
    }

}