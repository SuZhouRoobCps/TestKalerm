package com.example.learnkt.proxy

class Dog :Animals {
    override fun sounds(name: String): String {
        println("$name 再叫")
        return "汪汪汪"
    }


    override fun eat(name: String): String {
        println("$name 再吃" )
        return "muniangmuniang"
    }
}