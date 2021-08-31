package com.example.learnkt

import android.util.Log
import kotlinx.coroutines.*

class Test511_1 {
    fun body(str: String) {
        println(str)
    }
}

fun html(init: Test511_1.() -> Unit) {
    val test5111 = Test511_1()
//    test5111.init()
    init(test5111)
}

fun main() {
//    html { body("xxxxx") }
//    val scope = CoroutineScope(Dispatchers.Unconfined)
//    scope.launch {
//        test5_25_1()
//    }
//    Thread.sleep(1000)
//    val t=ThreadImp("xxx")
//    t.start()

    val str="1000"
    val binaryToHex = TestMaina.binaryToHex(str)
    println("$binaryToHex")
}
class ThreadImp(name:String):Thread(name){
    var index:Int=0;
    override fun run() {
        super.run()
       while (index<5){
           println("------1")
           val threadImp2 = ThreadImp2("zizizi")
           threadImp2.start()
           threadImp2.join()
           println("-------2")
           index++
       }

    }

}

class ThreadImp2(name:String):Thread(name){

    override fun run() {
        super.run()
        println("sssss"+Thread.currentThread().name)
        Thread.sleep(1000)
        println("sssss")

    }

}


suspend fun test5_25() {
    withContext(Dispatchers.IO) {
        delay(100)
        println("${Thread.currentThread().name}11111")
    }
    withContext(Dispatchers.Default) {
        delay(100)
        println("${Thread.currentThread().name}22222")

    }
}

suspend fun test5_25_1() {
    test5_25()
    println("${Thread.currentThread().name}44444")
    coroutineScope {
        launch {
            delay(100)
            println("${Thread.currentThread().name}333")

        }
    }
}

