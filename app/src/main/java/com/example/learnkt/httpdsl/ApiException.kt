package com.example.learnkt.httpdsl

import android.os.Message
import retrofit2.HttpException
import java.lang.Exception
import java.lang.StringBuilder

class ApiException : Exception {
    var msg: String? = "网络问题"
    var errorCode = 0

    private constructor(code: Int, throwable: Throwable) : super(throwable) {
        msg = throwable.message
        errorCode = code
    }

    constructor(message: String?, code: Int = 500) : super(message) {
        msg = message
        errorCode = code
    }

    companion object{
        const val _500_SERVICE =500
        const val UNKNOW_ERROR =-1

        fun formatException(e:Throwable):ApiException{
            val apiException:ApiException
                when(e){
                    is HttpException ->{
                        apiException= ApiException(e.code(),e)
                        apiException.msg="网络问题"
                    }




                }

         return   ApiException("x")
        }


    }



}