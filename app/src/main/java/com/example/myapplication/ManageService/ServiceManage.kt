package com.example.myapplication.ManageService

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.myapplication.CustomDialog
import com.example.myapplication.Database.AppDatabase
import com.example.myapplication.Database.Entity.AircraftEntity
import com.example.myapplication.Database.Entity.SystemEntity
import com.example.myapplication.Database.Entity.TechnicalOrderEntity
import com.example.myapplication.Response.MasterResponse
import com.example.myapplication.Service.MasterService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.create


class ServiceManage(context: Context) : BaseAPI(context) {
    val TAG: String = "ServiceManage"

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(create())
            .build()
    }

    fun getTechnicalOrderMaster(callback: OnResponseServiceCallBack) {
        val common: Repository
        val service = Repository.create<MasterService>(BaseUrl)
        val call = service.getMasterTechnicalOrder()
        var resMasterTechnical: String = "Default Value. "
        setCaller(call)
        enqueue(call , callback)
//        call.enqueue(object : Callback<MasterResponse> {
//                override fun onResponse(
//                    call: Call<MasterResponse>,
//                    response: Response<MasterResponse>
//                ) {
//                    var code = ResponseCode.from(response.code())
//                    resMasterTechnical = when (code) {
//                        ResponseCode.SUCCESS -> insertTechnicalOrder(response)
//                        ResponseCode.SERVICE_UNAVAILABLE -> code.toString()
//                        ResponseCode.INTERNAL_SERVER_ERROR -> code.toString()
//
//                        else -> code.toString()
//                    }
//                    CustomDialog(resMasterTechnical).show(fragmentManager, "Dialog")
//                }
//                override fun onFailure(call: Call<MasterResponse>, t: Throwable) {
//                    resMasterTechnical = t.message.toString()
//
//                }
//            })
    }

    fun insertTechnicalOrder(response: Response<MasterResponse>): String {
        try {
            var responses: MasterResponse = response.body()!!
            val db = AppDatabase(context)
            for (item in responses.message) {
                val launch = GlobalScope.launch {
                    db.technicalOrderDao().insertTechnicalOrder(
                        TechnicalOrderEntity(
                            Integer.parseInt(item.id),
                            item.created_date,
                            item.full_name
                        )
                    )
                }
                db.close()
            }
            return response.message().toString()
        }
        catch (e: Exception){
            return e.message.toString()
        }

    }

    fun getSystemMaster(): String {
        val service = buildRetrofit().create(MasterService::class.java)
        val call = service.getMasterSystem()
        var resMasterSystem: String = "Default Value. "
        call.enqueue(object : Callback<MasterResponse> {
            override fun onResponse(
                call: Call<MasterResponse>,
                response: Response<MasterResponse>
            ) {
                var responses: MasterResponse = response.body()!!
                val db = AppDatabase(context)
                for (item in responses.message) {
                    GlobalScope.launch {
                        db.systemDao().insertSystem(
                            SystemEntity(
                                Integer.parseInt(item.id),
                                item.created_date,
                                item.full_name
                            )
                        )
                        var data = db.systemDao().getMasterSystem()

                        data?.forEach {
                            println(it)
                            Log.i(TAG, it.fullName)
                        }

                    }
                }
                //  db.close()
                resMasterSystem = response.message().toString()
            }

            override fun onFailure(call: Call<MasterResponse>, t: Throwable) {
                resMasterSystem = t.message.toString()
            }
        })
        return resMasterSystem
    }

    fun getAircraftMaster(): String {
        val service = buildRetrofit().create(MasterService::class.java)
        val call = service.getMasterAircraft()
        var resMasterAircraft: String = "Default Value. "
        call.enqueue(object : Callback<MasterResponse> {
            override fun onResponse(
                call: Call<MasterResponse>,
                response: Response<MasterResponse>
            ) {
                var responses: MasterResponse = response.body()!!
                val db = AppDatabase(context)
                resMasterAircraft = response.message().toString()

                for (item in responses.message) {
                    GlobalScope.launch {
                        db.aircraftDao().insertAircraft(
                            AircraftEntity(
                                Integer.parseInt(item.id),
                                item.created_date,
                                item.full_name
                            )
                        )
                        var data = db.technicalOrderDao().getMasterTechnical()

                        data?.forEach {
                            println(it)
                            Log.i(TAG, it.fullName)
                        }

                    }
                }
                db.close()
            }

            override fun onFailure(call: Call<MasterResponse>, t: Throwable) {
                resMasterAircraft = t.message.toString()
            }
        })
        return resMasterAircraft
    }

    companion object {
        var BaseUrl = "https://hidden-harbor-84295.herokuapp.com"
    }




}