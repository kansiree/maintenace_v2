package com.example.myapplication.Service

import com.example.myapplication.Response.MasterResponse
import retrofit2.Call
import retrofit2.http.GET

interface MasterService {
    @GET("getMasterSystem")
    fun getMasterSystem(): Call<MasterResponse>

    @GET("getMasterTechnicalOrder")
    fun getMasterTechnicalOrder(): Call<MasterResponse>

    @GET("getMasterAircraft")
    fun getMasterAircraft(): Call<MasterResponse>

    @GET("getDetail")
    fun getDetail(): Call<MasterResponse>
}