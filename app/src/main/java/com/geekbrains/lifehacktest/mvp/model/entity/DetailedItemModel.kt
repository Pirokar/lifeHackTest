package com.geekbrains.lifehacktest.mvp.model.entity

import com.google.gson.annotations.SerializedName

class DetailedItemModel: ShortItemModel() {
    @SerializedName("description")
    var description = ""

    @SerializedName("lat")
    var latitude = 0f

    @SerializedName("lon")
    var longitude = 0f

    @SerializedName("www")
    var www = ""

    @SerializedName("phone")
    var phone = ""
}