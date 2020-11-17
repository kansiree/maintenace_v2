package com.example.myapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Database.Dao.AircraftDao
import com.example.myapplication.Database.Dao.SystemDao
import com.example.myapplication.Database.Dao.TechnicalOrderDao
import com.example.myapplication.Database.Entity.AircraftEntity
import com.example.myapplication.Database.Entity.SystemEntity
import com.example.myapplication.Database.Entity.TechnicalOrderEntity

@Database(entities = arrayOf(SystemEntity::class,AircraftEntity::class,TechnicalOrderEntity::class), version = 3,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun technicalOrderDao(): TechnicalOrderDao
    abstract fun systemDao(): SystemDao
    abstract fun aircraftDao(): AircraftDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "test1.db"
        ).build()
    }
}