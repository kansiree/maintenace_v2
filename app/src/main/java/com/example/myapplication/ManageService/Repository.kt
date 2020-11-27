package com.example.myapplication.ManageService

import android.text.TextUtils
import com.example.myapplication.MyApplication
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Repository {
    var retrofit: Retrofit? = null
    private val TAG = "NetworkConnectionManager"
    private val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

    companion object {
        const val CONNECT_TIMEOUT = "CONNECT_TIMEOUT"
        const val READ_TIMEOUT = "READ_TIMEOUT"
        const val WRITE_TIMEOUT = "WRITE_TIMEOUT"
        inline fun <reified T> create(endpoint: String): T {
            var res = Repository()
            res.retrofit = res.buildRetrofit(endpoint)
            return res.retrofit!!.create(T::class.java)
        }
    }



    val timeoutInterceptor = Interceptor { chain ->
        val request = chain.request()

        var connectTimeout = chain.connectTimeoutMillis()
        var readTimeout = chain.readTimeoutMillis()
        val writeTimeout = chain.writeTimeoutMillis()


        val connectNew = request.header(CONNECT_TIMEOUT)
        val readNew = request.header(READ_TIMEOUT)
        val writeNew = request.header(WRITE_TIMEOUT)

        if (!TextUtils.isEmpty(connectNew)) {
    //        connectTimeout = Integer.valueOf(connectNew)
        }


        if (!TextUtils.isEmpty(readNew)) {
     //       readTimeout = Integer.valueOf(readNew)
        }


        if (!TextUtils.isEmpty(writeNew)) {
   //         readTimeout = Integer.valueOf(writeNew)
        }


        chain.withConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            .withReadTimeout(readTimeout, TimeUnit.MILLISECONDS)
            .withWriteTimeout(writeTimeout, TimeUnit.MILLISECONDS)
            .proceed(request)
    }


    private val gson = GsonBuilder()
        .setDateFormat(DATE_FORMAT)
        .create()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(timeoutInterceptor)
        .connectTimeout(MyApplication.settings.connectionTimeOut, TimeUnit.MILLISECONDS)
        .readTimeout(MyApplication.settings.readTimeOut, TimeUnit.MILLISECONDS)
        .writeTimeout(MyApplication.settings.writeTimeOut, TimeUnit.MILLISECONDS)
        .build()

    fun createRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

     fun buildRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}