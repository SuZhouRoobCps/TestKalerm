package com.example.learnkt

import android.util.Log
import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.Exception
import kotlin.system.measureTimeMillis

/**
 *  runningBlock 用来连接阻塞与非阻塞的世界
 *  可以创建一个阻塞当前线程的协程
 *
 *   launch 协程  返回Job  join cancel
 *   async 创建一个返回值的非阻塞  await
 *
 *
 */
fun main() {
    test12()
}

suspend fun doNothing1(): Int {
    delay(1000)
    return 1;
}

suspend fun doNothing2(): Int {
    delay(2000)
    return 2
}


fun test11() {
    runBlocking {
        launch {
            val measureTimeMillis = measureTimeMillis {
                val doNothing1 = async(start = CoroutineStart.LAZY) { doNothing1() }
                val doNothing2 = async(start = CoroutineStart.LAZY) { doNothing2() }
                doNothing1.start()
                doNothing2.start()
                delay(100)
                println("${doNothing1.await() + doNothing2.await()}  计算结果")

            }
            println("$measureTimeMillis millis")

        }


    }


}

suspend fun concurrentSun(): Int {
    return coroutineScope {
        val one = async { doNothing1() }
        val two = async { doNothing2() }
        one.await() + two.await()
    }

}

fun test12() {
    runBlocking {

        val time = measureTimeMillis {


            val one = async(CoroutineName("name1")) {
                doNothing1()


            }
            val two = async(CoroutineName("name2")) {
                doNothing2()
                println("执行了吗")
                12
            }
            println("hhhh ${one.await() + two.await()}")

        }
        println("$time")
    }

}

suspend fun failedConcurrentSum(): Int {

    var result = coroutineScope {
        val data1 = async<Int> {
            try {
                delay(Long.MAX_VALUE)
                122
            } finally {
                println("first Child was cancelled")
            }
        }

        val data2=async<Int> {
            println("second child throw an exception")
            throw ArithmeticException()
        }

        data1.await()+data2.await()
    }
    return result;

}
