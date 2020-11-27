package com.example.myapplication.ManageService

interface OnResponseServiceCallBack{
fun onSuccess(model: Any?)

fun onFailure(message: String?, t: Throwable?)

fun onServiceFailure(returnCode: String, returnMessage: String)
}