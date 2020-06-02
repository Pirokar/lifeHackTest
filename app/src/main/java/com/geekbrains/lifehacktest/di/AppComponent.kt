package com.geekbrains.lifehacktest.di

import com.geekbrains.lifehacktest.di.modules.ApiModule
import com.geekbrains.lifehacktest.di.modules.AppModule
import com.geekbrains.lifehacktest.di.modules.CacheModule
import com.geekbrains.lifehacktest.framework.ui.activity.DetailedScreenActivity
import com.geekbrains.lifehacktest.framework.ui.adapter.ItemsListRVAdapter
import com.geekbrains.lifehacktest.mvp.model.repo.ModelsRepo
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CacheModule::class,
        ApiModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(itemsListRVAdapter: ItemsListRVAdapter)
    fun inject(modelsRepo: ModelsRepo)
    fun inject(detailedScreenActivity: DetailedScreenActivity)
}