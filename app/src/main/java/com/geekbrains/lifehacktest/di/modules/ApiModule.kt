package com.geekbrains.lifehacktest.di.modules

import android.widget.ImageView
import com.geekbrains.App
import com.geekbrains.lifehacktest.framework.network.AndroidNetworkStatus
import com.geekbrains.lifehacktest.framework.ui.image.GlideImageLoader
import com.geekbrains.lifehacktest.mvp.model.api.IDataSource
import com.geekbrains.lifehacktest.mvp.model.image.IImageLoader
import com.geekbrains.lifehacktest.mvp.model.network.NetworkStatus
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun api(): IDataSource {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://megakohz.bget.ru/test_task/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(IDataSource::class.java)
    }

    @Singleton
    @Provides
    fun networkStatus(app: App): NetworkStatus {
        return AndroidNetworkStatus(app)
    }

    @Provides
    fun imageLoader(): IImageLoader<ImageView> {
        return GlideImageLoader()
    }
}