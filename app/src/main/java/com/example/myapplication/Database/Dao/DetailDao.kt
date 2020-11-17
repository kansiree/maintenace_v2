package com.example.myapplication.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Database.Entity.DetailEntity

@Dao
interface DetailDao {
    @Insert
    fun insertDetailEntity(vararg item : DetailEntity)

    @Delete
    fun deleteDetailEntity(item : DetailEntity)

    @Query("SELECT * FROM detailEntity")
    fun getDetailEntity():List<DetailEntity>

    @Query("SELECT * FROM detailEntity WHERE fullName LIKE :fullname")
    fun findByFullName(fullname: String): LiveData<List<DetailEntity>>
}