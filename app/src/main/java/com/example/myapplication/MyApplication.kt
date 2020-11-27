package com.example.myapplication

class MyApplication {

    companion object{
        var BaseUrl = "https://hidden-harbor-84295.herokuapp.com"

        val settings = Settings()
    }

    class Settings{
        val connectionTimeOut = 1000L * 1 * 60
        val readTimeOut = 1000L * 1 * 60
        val writeTimeOut = 1000L * 2 * 60
    }
}