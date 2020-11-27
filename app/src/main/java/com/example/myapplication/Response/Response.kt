package com.example.myapplication.Response

import com.example.myapplication.Service.BaseModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//data class MasterResponse(@SerializedName("error") var error:Int =0
//                          ,@SerializedName("message") var message:List<Master>)
data class MasterResult(var result: List<Master>):BaseModel()

data class MasterResponse( var error:Int =0
                          ,var message:List<Master>):Serializable