package com.example.myapplication.Database.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TechnicalOrderEntity(
    @PrimaryKey val ID: Int,
    @ColumnInfo(name = "createdDate") val createdDate: String,
    @ColumnInfo(name = "fullName") val fullName: String
)