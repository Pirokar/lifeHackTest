package com.geekbrains.lifehacktest.mvp.model.entity.database.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.geekbrains.lifehacktest.mvp.model.entity.DetailedItemModel

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomShortItemModel::class,
        parentColumns = ["id"],
        childColumns = ["id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomDetailedItemModel(
    @PrimaryKey var id: String,
    var name: String,
    var img: String,
    var description: String,
    var latitude: Float,
    var longitude: Float,
    var www: String,
    var phone:String) {

    fun mapToSimpleModel(): DetailedItemModel {
        val model = DetailedItemModel()
        model.id = id
        model.name = name
        model.img = img
        model.description = description
        model.latitude = latitude
        model.longitude = longitude
        model.www = www
        model.phone = phone

        return model
    }
}