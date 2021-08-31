package com.example.learnkt.proxy

import android.content.ClipboardManager
import android.content.Context
import android.os.IBinder
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*

fun main() {
    val dog: Dog = Dog()


    val proxy = Proxy.newProxyInstance(
        dog.javaClass.classLoader, dog.javaClass.interfaces
    ) { proxy, method, args ->
        println(method.name)
        args?.forEach {
            println(it.toString())
        }
        println("open")
        method.invoke(dog, args[0])
        println("stop")
        null
    } as Animals

    proxy.sounds("张三")
    proxy.eat("lisi")
    println("xxx")
    println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
    val cInstance=TestJavaProxy()
    cInstance.main2()



}

fun t1(context: Context){
//    context.getSystemService("")
//    ClipboardManager
}


