package com.example.learnkt.jetpack

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * 这里一般用来做数据的请求
 */
class MainRepository {

    suspend fun getData(): List<String> {
        return withContext(Dispatchers.IO) {
            delay(2000)
            mutableListOf("aaa", "bbbb")
        }
    }

}