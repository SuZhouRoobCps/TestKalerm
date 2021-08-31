package com.example.learnkt.httpdsl

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET

interface HttpApi {

    @GET("xxxxx")
    suspend fun login()


    fun login2(@Body body:RequestBody):retrofit2.Call<CommonBean<LoginBean>>
}