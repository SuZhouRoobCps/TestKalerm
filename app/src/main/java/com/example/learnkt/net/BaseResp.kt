package com.example.learnkt.net

/**
 * json 返回的基本类型
 */
data class BaseResp<T>(
    var errorCode:Int,
    var errorMsg:String,
    var data:T,
    var dataState:DataState,
    var error:Throwable?=null
) {

}

enum class DataState{
    STATE_LOADING,
    STATE_SUCCESS,
    STATE_EMPTY,
    STATE_ERROR,
    STATE_FAILED
}