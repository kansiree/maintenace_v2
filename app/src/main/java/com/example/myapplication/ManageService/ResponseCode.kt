package com.example.myapplication.ManageService

 enum class ResponseCode (var value: Int) {
    SUCCESS(200),
    SERVICE_UNAVAILABLE(503),
     INTERNAL_SERVER_ERROR(500);
     companion object {
         fun from(findValue: Int): ResponseCode = ResponseCode.values().first { it.value == findValue }
     }
}

