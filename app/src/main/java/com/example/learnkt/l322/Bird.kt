package com.example.learnkt.l322

class Bird(name: String, age: Int, var weight: Int) : Animal(name, age) {

    override fun doCalls(): String {

        return "i am $name,$age's old,ji ji ji ji"
    }

    override fun doEat(weightssss: Int): Int {
        return weight/age
    }

}