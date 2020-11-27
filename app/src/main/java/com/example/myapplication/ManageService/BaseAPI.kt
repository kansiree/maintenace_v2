package com.example.myapplication.ManageService

import android.content.Context
import com.example.myapplication.Service.BaseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseAPI(var context: Context) {
    @Transient
    private var caller: Call<*>? = null

    fun setCaller(call: Call<*>) {
        caller = call
    }

    fun getCaller(): Call<*>? {
        return caller
    }

    fun <T> enqueue(call: Call<T>, callBack: OnResponseServiceCallBack) {
        call.enqueue(object : Callback<T> {

            override fun onFailure(call: Call<T>, t: Throwable) {
                var message = onFailureMessage(t)
                callBack.onFailure(message, t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    var code = ResponseCode.from(response.code())
//                    var model = response!!.body() as BaseModel
                    when (code) {
                        ResponseCode.SUCCESS -> {
                          //  model = onCustomModel(model,call, response)
                            callBack.onSuccess(response.body())
                        }
                        else -> {
                            callBack.onFailure(
                                "Error for for:${response.code()}",
                                java.lang.Exception("Error for for:${response.code()}")
                            )
                        }
                    }
                }

            }

        })
    }

    open fun onFailureMessage(t: Throwable?): String {
        var message = t?.message
//        if (t is ConnectException || t is UnknownServiceException || t is UnknownHostException || t is SocketTimeoutException) {
//            message = R.string.common_error_network.toStringResource(context)
//        }
        return message.toString()
    }

    open fun <T> onCustomModel(model: BaseModel,call: Call<T>?, response: Response<T>?):BaseModel {
        return model
    }
}