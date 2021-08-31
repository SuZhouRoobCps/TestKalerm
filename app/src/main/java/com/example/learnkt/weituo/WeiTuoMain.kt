package com.example.learnkt.weituo

fun main() {
    /**
     * save to sql
    save to GreenDao


     委托类
     */
    UniversalDB(SqlDB()).save()
    UniversalDB(GreenDaoDB()).save()
    var list= MutableList(10) { index -> "xxx$index"
    }
    mutableListOf<String>()
    LogList(
        {
            println("xxx")
        },
       list
    ).getAndLog(1)
}


/**
 * @link{}
 */
fun<T> MutableList<T>.log(index:Int,log:()->Unit)= run {
    log()
    get(index)
}