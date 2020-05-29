package com.geekbrains.lifehacktest.mvp.model.entity.database.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.geekbrains.lifehacktest.mvp.model.entity.DetailedItemModel
import com.geekbrains.lifehacktest.mvp.model.entity.ShortItemModel

@Entity
data class RoomShortItemModel(
    @PrimaryKey var id: String,
    var name: String,
    var img: String
) {
    fun mapToSimpleModel(): DetailedItemModel {
        val model = DetailedItemModel()
        model.id = id
        model.name = name
        model.img = img

        return model
    }
}