package com.example.learnkt.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.AbstractExecutorService
import java.util.concurrent.TimeUnit

object RetrofitManager {
    private val mOkClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .followRedirects(false)
        .cookieJar(LocalCookieJar())
        .build()

    private var mRetrofit: Retrofit? = null

    fun initRetrofit(): RetrofitManager {
        mRetrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .client(mOkClient)
//            .addConverterFactory()
            .build()
        return this
    }

    fun <T> getService(service: Class<T>): T {
        if (mRetrofit == null) {
            throw UninitializedPropertyAccessException("Retrofit必须初始化")
        } else {
            return mRetrofit!!.create(service)
        }
    }
}