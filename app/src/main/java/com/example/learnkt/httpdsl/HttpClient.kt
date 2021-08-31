package com.example.learnkt.httpdsl

import android.util.ArrayMap
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class HttpClient {

    private object Client {
        val builder = HttpClient()
    }

    companion object {
        fun getInstance() = Client.builder
    }

    private val apiMap by lazy {
        ArrayMap<String, Any>()
    }

    private var interceptors = arrayListOf<Interceptor>()
    private var converterFactorys = arrayListOf<Converter.Factory>()

    fun setInterceptors(list: MutableList<Interceptor>?): HttpClient {
        interceptors.clear()
        if (!list.isNullOrEmpty()) {
            interceptors.addAll(list)
        }
        return this
    }

    fun setConverFactorys(list: MutableList<Converter.Factory>?): HttpClient {
        converterFactorys.clear()
//        converterFactorys.add(GsonConverterFactory.create()) //至少添加一个默认的解析器
        if (!list.isNullOrEmpty()) {
            converterFactorys.addAll(list)
        }
        return this
    }


    fun <T> createRetrofitApi(
        baseUrl: String = "xxxx",
        clazz: Class<T>,
        needAddHeader: Boolean = true,
        showLog: Boolean = true

    ): T {

        val key = getApiKey(baseUrl, clazz)
        val api = apiMap[key] as T
        if (api == null) {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
            if (needAddHeader) {
//                builder.addInterceptor()
            }
            if (interceptors.isNullOrEmpty()) {
                interceptors.forEach {
                    builder.addInterceptor(it)
                }
            }
            if (showLog) {
//                builder.addInterceptor()
            }
            val rBuilder = Retrofit.Builder()
                .baseUrl("xxxx")
                .client(builder.build())
            if (converterFactorys.isEmpty()) {
//                converterFactorys.add(GsonConverterFactory.create())
            }
            converterFactorys.forEach {
                rBuilder.addConverterFactory(it)
            }
            val newApi = rBuilder.build().create(clazz)
            apiMap[key] = newApi
            return newApi
        }
        return api


    }


    fun <K> getApiKey(baseUrl: String, apiClass: Class<K>): String {
        return "apiKey_${baseUrl}_${apiClass.name}"
    }

    fun clearInterceptor(): HttpClient {
        interceptors.clear()
        return this
    }

    fun clearConverterFactory(): HttpClient {
        converterFactorys.clear()
        return this
    }

    fun clearAllApi(): HttpClient {
        apiMap.clear()
        return this
    }

    val defaultApi: HttpApi
        get() {
            return HttpClient.getInstance().createRetrofitApi(clazz = HttpApi::class.java)
        }


}