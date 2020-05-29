package com.geekbrains.lifehacktest.mvp.model.entity.database.room.dao

import androidx.room.*
import com.geekbrains.lifehacktest.mvp.model.entity.database.room.RoomShortItemModel

@Dao
interface ShortItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shortItem: RoomShortItemModel)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg shortItem: RoomShortItemModel)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shortItems: List<RoomShortItemModel>)
    
    @Update
    fun update(shortItem: RoomShortItemModel)

    @Update
    fun update(vararg shortItem: RoomShortItemModel)

    @Update
    fun update(shortItems: List<RoomShortItemModel>)
    
    @Delete
    fun delete(shortItem: RoomShortItemModel)
    
    @Delete
    fun delete(vararg shortItem: RoomShortItemModel)
    
    @Delete
    fun delete(shortItems: List<RoomShortItemModel>)

    @Query("SELECT * FROM RoomShortItemModel")
    fun getAll(): List<RoomShortItemModel>

    @Query("SELECT * FROM RoomShortItemModel WHERE id = :id LIMIT 1")
    fun getById(id: String): RoomShortItemModel?
}