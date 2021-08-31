package com.example.learnkt

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

fun isOdd(x: Int, x2: Int): Boolean = x % 2 == 0


fun main() {
//    val methodIsOdd: (Int, Int) -> Boolean = ::isOdd
//    val data= listOf<Int>(1,2,3,4,6)
//    println(data.filter(::isOdd))
//    println(data.filter {
//        isOdd(it)
//    })

//    var ad:TestAdapter= TestAdapter()
//    ad.setMListener{
//        i, s ->
//
//        println("$i $s")
//    }

    var s: String? = null
    var sb: StringBuffer = StringBuffer()
    sb.append(s ?: "xxx")
    println(sb)
    val str = String.format("%s %d%s", "copying", 50,"%")
    println(str)

}

/**
 *  coroutineScope 会继承外部的协程作用域并创建一个子作用域
 *  coroutineScope 是一个挂起函数，
 *
 */
suspend fun testv(){

    coroutineScope{
        launch {
            delay(1000)
        }

      val job=  async {
            11
        }
        job.await()
    }

    measureTimeMillis {


    }


}