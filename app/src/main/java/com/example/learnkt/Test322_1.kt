package com.example.learnkt

import kotlinx.coroutines.*

fun main() {
//    var test322=Test322()
//    test322.main()
//    GlobalScope.launch {
//        delay(1000L)
//        println("world")
//
//    }
//    println("Hello,")
//    Thread.sleep(2000L)
    test6()

//    runBlocking {
//        launch {
//            repeat(100){
//                i->
//                delay(100)
//                println("$i")
//            }
//        }

//    }

}


fun main2() = runBlocking<Unit> {
    GlobalScope.launch {
        delay(1000)
        println("world")
    }
    println("Hello,")
    delay(2000)
}

fun main3() = runBlocking<Unit> {
    val job = GlobalScope.launch {
        delay(1000)
        println("world")
    }
    println("Hello")
    job.join()
}

/**
 * runBlocking 生成一个阻塞的线程执行 delay 非阻塞式的
 * runBlocking的主线程会一直阻塞直到内部的协成执行完毕
 */
fun main4() {
    runBlocking {
        GlobalScope.launch {
            delay(1000)
            print("world")
        }
        print("hello")
        delay(2000)
    }


    runBlocking {
        val launch = GlobalScope.launch {
            delay(1000)
            println("world")
        }
        print("Hello")
        launch.join()
    }

    runBlocking {
        launch {
            delay(1000)
            println("World")
        }
        print("Hello")

    }


}

/**
 * 本级携程的最后总是等待内部携程完成之后才会输出，
 * 其他的都按照时间执行
 */
fun test1() {
    runBlocking {
        launch {
            delay(600)
            println("task from runBlocking")
        }
        coroutineScope {
            launch {
                delay(500)
                println("task form nested launch")
            }
            delay(300)
            println("task from coroutine scope")
        }

        println("coroutine scope is over")
    }

}


fun test2() {
    runBlocking {
        var job = launch {
            repeat(1000) { i ->
                println("job:i am $i")
                delay(10)
            }

        }
        delay(1000)
        job.cancel()
//        job.join()
    }


}

/**
 * 取消是协作的
 *
 * 取消不了
 */
fun test3() {
    runBlocking {
        val startTime = System.currentTimeMillis();
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("${i++}")
                    nextPrintTime += 500
                }
            }
        }
        delay(1300)
        job.cancelAndJoin()

    }
    runBlocking {
        val startTime = System.currentTimeMillis();
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (isActive) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("${i++}")
                    nextPrintTime += 500
                }
            }
        }
        delay(1300)
        job.cancelAndJoin()

    }


}

/**
 * finally 中释放资源
 */
fun test4() {
    runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job:$i")
                    delay(500)
                }
            } finally {
                println("run finally")
            }

        }

        delay(1300)
        println("bu deng le")
        job.cancelAndJoin()
        println("can quit")
    }

    // withContext(NonCancellable) 如果没这个 则finally中 delay 之后的代码 不再执行
    runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job:$i")
                    delay(500)
                }
            } finally {
                withContext(NonCancellable) {
                    println("finally")
                    delay(1000)
                    println("delpysssss")
                }
            }

        }

        delay(1300)
        println("bu deng le")
        job.cancelAndJoin()
        println("can quit")
    }

}

fun test5() {
    runBlocking {
        var result = withTimeout(1500) {

            repeat(1000) { i ->
                println(i)
                delay(500)
            }
            "finish"
        }
        println(result)

    }

    runBlocking {
        val result = withTimeoutOrNull(1000) {
            repeat(1000) { index ->
                println("第${index}次")
                delay(100)
            }
            "结果"
        }
    }


}

fun test6() {
    runBlocking {
        val job = launch {
            try {
                var i = 0;
                while (i < 10) {
                    println("${i++} 我的value")
                }
                delay(1000)
            } finally {
                println("执行finally")
                delay(1000)
                println("xxxxx")
            }
        }
        println("delay 前")
        delay(3000)
        println("delay 后")
        job.cancelAndJoin()

    }


}