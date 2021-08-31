package com.example.learnkt.net

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

open class BaseRepository {
    suspend fun <T : Any> executeResp(
        resp: BaseResp<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null

    ): ResState<T> {
        return coroutineScope {
            if (resp.errorCode == 0) {
                successBlock?.let {
                    it()  //这里代表的就是执行自己的意思
                }
                ResState.Success(resp.data)
            } else {
                errorBlock?.let {
                    it()
                }
                ResState.Error(IOException(resp.errorMsg))
            }

        }
    }

}