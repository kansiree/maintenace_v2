package com.example.myapplication.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Database.Entity.AircraftEntity

@Dao
interface AircraftDao {
    @Insert
    fun insertAircraft(vararg air : AircraftEntity)

    @Delete
    fun deleteAircraft(tech : AircraftEntity)

    @Query("SELECT * FROM aircraftentity")
    fun getMasterAircraft():List<AircraftEntity>

    @Query("SELECT * FROM aircraftentity WHERE fullName LIKE :fullname")
    fun findByFullName(fullname: String): LiveData<List<AircraftEntity>>
}