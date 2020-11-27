package com.example.myapplication.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Database.Entity.TechnicalOrderEntity

@Dao
interface TechnicalOrderDao {
    @Insert
    fun insertTechnicalOrder(vararg tech: TechnicalOrderEntity)

    @Delete
    fun deleteTechnicalOrder(tech: TechnicalOrderEntity)

    @Query("SELECT * FROM TechnicalOrderEntity")
    fun getMasterTechnical(): List<TechnicalOrderEntity>

    @Query("SELECT * FROM TechnicalOrderEntity WHERE fullName LIKE :fullname")
    fun findByFullName(fullname: String): LiveData<List<TechnicalOrderEntity>>
}