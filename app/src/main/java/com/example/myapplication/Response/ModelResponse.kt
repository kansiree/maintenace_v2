package com.example.myapplication.Response

import com.google.gson.annotations.SerializedName

data class Master(
    @SerializedName("created_date") var created_date: String = "",
    @SerializedName("full_name") var full_name: String = "",
    @SerializedName("id") var id: String = ""
)