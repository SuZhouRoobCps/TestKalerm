package com.example.learnkt

import androidx.annotation.MainThread
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select
import java.lang.ArithmeticException
import java.lang.AssertionError

fun main() {
    test_414_5()
}

fun test414_1() {
    runBlocking {
        val channel = Channel<Int>()
        launch {
            for (i in 1..4) {
                channel.send(i * i)
            }
        }
        repeat(4) {
            println(channel.receive())
        }
        println("finished")

        launch {
            for (i in 1..5) {
                channel.send(i * i)
            }
            channel.close()
        }
        for (y in channel) {
            println(y)
        }
        println("Done!")

    }


}

/**
 *  launch 直接丢出异常 给 exceptionHandler 处理
 *  async 则需要等待 job。awati 自己处理
 */
fun test_414_2() {
    runBlocking {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("CoroutineExceptionHandler $throwable")
        }

        val job1 = GlobalScope.launch(exceptionHandler) {
            throw AssertionError()
        }

        val job2 = GlobalScope.async(exceptionHandler) {
            throw ArithmeticException()
        }

        joinAll(job1, job2)
    }

}

/**
 * 13542
 */
fun test_414_3() {
    runBlocking {

        launch {
            println("1")
            launch {
                println("5")
                delay(1000)
                println("2")

            }
            println("3")
            delay(500)
            println("4")
        }


    }
}

fun test_414_4() {
    runBlocking {
        val job = launch {
            repeat(10) {
                delay(1000)
                println(it)
            }
            println("????")

        }
        delay(20)
//        job.cancel()
//        job.join()
        job.cancelAndJoin()

    }
}

fun test_414_5() {
    runBlocking {
        produce {
            repeat(5) {
                println("发送$it")
                send(it)
            }

        }.consumeEach {
            println("接手$it")
        }


    }


}

fun CoroutineScope.fizz() = produce<String> {
    while (true) {
        delay(300)
        send("Fizz")
    }
}

fun CoroutineScope.buzz() = produce<String> {
    while (true) {
        delay(500)
        send("Buzz")
    }
}

suspend fun selectFizzOrBuzz(a: ReceiveChannel<String>, b: ReceiveChannel<String>) {
    select<Unit> {
        a.onReceive { s ->
            println("a$s")
        }
        b.onReceive { s ->
            println("b$s")
        }
    }
}

fun test_415_1(){
    runBlocking {
        val fizz=fizz()
        val bizz=buzz()
        repeat(7){
            selectFizzOrBuzz(fizz,bizz)
        }
        coroutineContext.cancelChildren()

    }
}


