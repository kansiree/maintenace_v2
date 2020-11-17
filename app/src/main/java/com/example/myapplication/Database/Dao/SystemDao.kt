package com.example.myapplication.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Database.Entity.SystemEntity

@Dao
interface SystemDao {
    @Insert
    fun insertSystem(vararg sys : SystemEntity)

    @Delete
    fun deleteSystem(sys : SystemEntity)

    @Query("SELECT * FROM systementity")
    fun getMasterSystem():List<SystemEntity>

    @Query("SELECT * FROM systementity WHERE fullName LIKE :fullname")
    fun findByFullName(fullname: String): LiveData<List<SystemEntity>>
}