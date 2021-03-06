package com.example.learnkt

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.math.log
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.flow.zip as zip

fun main() {
    runBlocking(CoroutineName("main?")) {
//        log("started main coroutine")
//        val v1 = async(CoroutineName("v1coroutine")) {
//            delay(500)
//            log("Computing v1")
//            "252s"
//        }
//        val v2 = async(CoroutineName("v2coroutine")) {
//            delay(1000)
//            log("computing v2")
//            222
//        }
//        log(" the answer for v1 plus v2 =${v1.await()} + ${v2.await()}")

        test_413_1()
    }


}

fun log(msg: String) {
    println("${Thread.currentThread().name}  $msg")
}

fun simple(): Sequence<Int> {

    val result = sequence<Int> {
        for (i in 1..3 step 1) {
            Thread.sleep(100)
            yield(i)
        }
    }

    return result;
}

fun test409_2() {
    simple().forEach { it -> log("$it") }
    runBlocking {
        simple2().collect { value ->
            log("$value")
        }

    }

}

fun simple2(): Flow<Int> {
    val result = flow<Int> {
        for (i in 1..10 step 1) {
            emit(i)
            delay(1000)
        }

    }
    return result;
}

fun test_412() {
    runBlocking {
        simple2().collect { value ->
            println(value)
        }
    }

}

fun simple3(): Flow<Int> = flow {
    repeat(10) { i ->
        emit(i)
        delay(100)
    }

}

fun test_412_1() {
    runBlocking {
        withTimeoutOrNull(600) {
            simple3().collect { value ->
                println(value)
            }
        }

    }

}

fun test_412_2() {
    runBlocking {
        (1..5).asFlow().map { it -> "$it" }
            .collect { value ->
                println("$value")
            }
    }

}

fun test_412_3() {
    runBlocking {
        (1..5).asFlow()
            .transform { value ->
                emit("$value")
                emit("${value * value}")
                emit("${value % 2}")
                emit(value)
            }
            .collect { value ->
                println(value is String)
            }


    }

}

fun test_412_4() {
    runBlocking {
        flow {
            try {
                emit(1)
                emit(2)
                emit("11")
                emit("22")
            } finally {
                println("???")
            }
        }.take(2).collect {
            println(it)
        }


    }
}

var testData = ArrayList<String>()


/**
 * ??????????????? list ???
 */
fun test_412_5() {
    runBlocking {
        flow<List<String>> {
            (1..5).asFlow()
                .map { "$it" }
                .toList()

        }
    }
}

/**
 * ??????????????????????????????
 */
fun test_412_6() {
    runBlocking {
        flow {
            for (i in 1..6) {
                delay(1000)
                emit(i)
            }

        }.flowOn(Dispatchers.Default).collect() { value ->
            println(value)
        }


    }
}

/**
 * buffer ??? flow ?????????????????????????????? ?????????????????????
 *
 * conflate ?????????????????????????????????????????????????????????????????????????????????????????? ????????? 1 2  4  6 ???????????????????????? ????????????
 */
fun test_412_7() {
    runBlocking {
        val time = measureTimeMillis {
            flow {
                for (i in 1..6) {
                    delay(1000)
                    emit(i)
                }
            }
//                .flowOn(Dispatchers.Default)
//                .buffer()
                .conflate()
                .collect() { value ->
                    delay(2000)
                    println(value)
                }

        }

        println(time)
    }
}

/**
 * zip  ????????????????????????
 */
fun test_413_1() {
    runBlocking {
        val nums = (1..2).asFlow()
        val strs = flowOf("1s", "2s", "3s")

        nums.zip(strs) { i: Int, s: String ->
            "$i  xxx  $s"
        }.collect { value ->
            println(value)
        }

    }

}

/**
 * combine ?????????????????? ????????????????????????value it
 */
fun test_413_2() {
    runBlocking {
        val nums = (1..3).asFlow().onEach { delay(1000) }
        val strs = flowOf("1s", "2s", "3s")
        nums.zip(strs) { i, s ->
            "$i + $s=111"
        }.collect { value ->
            println(value)
        }
        nums.combine(strs) { a: Int, b: String ->
            "$a$b"
        }.collect {
            println(it)
        }
    }

}

fun requestFlow(i: Int): Flow<String> {
    return flow {
        emit("?????? ??????$i")
        delay(500)
        emit("?????? ??????")
    }
}

/**
 * flatmapconcat??????????????????
 * flatmapmerge ????????????????????????
 */
fun test_413_3() {
    runBlocking {
        (1..3).asFlow().onEach { delay(100) }
            .flatMapConcat { value -> requestFlow(value) }
            .collect { value ->
                println(value)
            }
    }
}