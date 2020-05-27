package com.geekbrains.lifehacktest.mvp.model.entity

import com.google.gson.annotations.SerializedName

open class ShortItemModel {
    @SerializedName("id")
    var id = ""

    @SerializedName("name")
    var name = ""

    @SerializedName("img")
    var img = ""
}