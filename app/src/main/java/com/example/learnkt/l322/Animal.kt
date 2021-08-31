package com.example.learnkt.l322

abstract class Animal( val name: String, var age: Int) {
    /**
     * 叫声
     */
    abstract fun doCalls(): String

    open fun doEat(weight: Int): Int {

        return weight / age
    }

}