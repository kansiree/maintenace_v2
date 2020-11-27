package com.example.myapplication.Service

open abstract class BaseModel {
    var returnCode: String = ""
    var returnMessage: String = ""

    companion object {
        const val CODE_SUCCESS = "0000"
        const val CODE_FORCE_UPDATE = "1100"
    }
}