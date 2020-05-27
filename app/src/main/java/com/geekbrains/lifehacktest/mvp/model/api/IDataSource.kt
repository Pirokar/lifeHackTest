package com.geekbrains.lifehacktest.mvp.model.api

import com.geekbrains.lifehacktest.mvp.model.entity.DetailedItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.ShortItemModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {
    @GET("test.php")
    fun getMainItemModel(@Query("id") id: String): Single<Array<DetailedItemModel>>

    @GET("test.php")
    fun getItemsList(): Single<Array<ShortItemModel>>
}