package com.example.learnkt

import com.example.learnkt.l322.Bird
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.math.abs



    fun main(){
        var number1=1;
        var number2=2;
        println(number1+number2)
        var aBird=Bird("喜鹊",2,10)
        var calledS=aBird.doCalls()
        print("${aBird.doCalls()} ${aBird.doEat(0)}")
        val b=""
        val a=(b==""?:"0")
        println()
        println("${a is Boolean}")

        val array=Array(5){
                i->
            test1_510(i)
        }

        for ( i in array.indices){
            println(array[i])
        }
        array.forEachIndexed { index, s ->
            println("$index -< $s")
        }


        val a1: Int = 10000
        val boxedA: Int? = a1
        val anotherBoxedA: Int? = a1
        print(boxedA == anotherBoxedA)
        print(boxedA === anotherBoxedA)
        var car=Cat();
        car.bark()
        car.eat()
        var test_i_1="xxx"
        StaFile.name
        StaFile.isOk("xxx")
    }


fun test1_56(){
    CoroutineScope(Dispatchers.Main+ Job()).launch {

        var name:String?
        name=null
        name?.let {
            it.length
            name.length
        }
        println(1::class.simpleName)
    }



}
fun test1_510(i:Int):String{
    return when(i){
        1 -> "1"
        2-> "2"
        else -> "2"
    }
}
