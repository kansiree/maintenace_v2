package com.example.myapplication.Response

import com.google.gson.annotations.SerializedName

data class MasterResponse(@SerializedName("error") var error:Int =0
                          ,@SerializedName("message") var message:List<Master>)