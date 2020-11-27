package com.example.myapplication.Database

import android.content.Context
import com.example.myapplication.Database.Entity.AircraftEntity
import com.example.myapplication.Database.Entity.SystemEntity
import com.example.myapplication.Database.Entity.TechnicalOrderEntity
import com.example.myapplication.ManageService.Repository
import com.example.myapplication.ManageService.ResponseCode
import com.example.myapplication.Response.MasterResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DatabaseManage (var context: Context){
    var connection: AppDatabase = AppDatabase(context)

    private fun closeConnection(){
        if (connection != null){
            connection.close()
        }
    }

    fun insertTechnicalOrder (responses: MasterResponse):String{
        try {
            for (item in responses.message) {
                val launch = GlobalScope.launch {
                    connection.technicalOrderDao().insertTechnicalOrder(
                        TechnicalOrderEntity(
                            Integer.parseInt(item.id),
                            item.created_date,
                            item.full_name
                        )
                    )
                }
                connection.close()
            }
            return ResponseCode.SUCCESS.name
        }
        catch (e: Exception){
            return e.message.toString()
        }
    }

    fun insertSystem (responses: MasterResponse):String{
        try {
            for (item in responses.message) {
                val launch = GlobalScope.launch {
                    connection.systemDao().insertSystem(
                        SystemEntity(
                            Integer.parseInt(item.id),
                            item.created_date,
                            item.full_name
                        )
                    )
                }
                connection.close()
            }
            return ResponseCode.SUCCESS.name
        }
        catch (e: Exception){
            return e.message.toString()
        }
    }

    fun insertAircraft (responses: MasterResponse):String{
        try {
            for (item in responses.message) {
                val launch = GlobalScope.launch {
                    connection.aircraftDao().insertAircraft(
                        AircraftEntity(
                            Integer.parseInt(item.id),
                            item.created_date,
                            item.full_name
                        )
                    )
                }
                connection.close()
            }
            return ResponseCode.SUCCESS.name
        }
        catch (e: Exception){
            return e.message.toString()
        }
    }

    fun insertDetail (responses: MasterResponse):String{
//        try {
//            for (item in responses.message) {
//                val launch = GlobalScope.launch {
//                    connection.aircraftDao().insertAircraft(
//                        AircraftEntity(
//                            Integer.parseInt(item.id),
//                            item.created_date,
//                            item.full_name
//                        )
//                    )
//                }
//                connection.close()
//            }
//            return ResponseCode.SUCCESS.name
//        }
//        catch (e: Exception){
//            return e.message.toString()
//        }
        return ""
    }
}