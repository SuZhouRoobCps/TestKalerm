package com.example.learnkt

class TestInterfaceImp :TestInterface {
    override val data1: String
        get() = "xxxx"
    override var data2: String=""
        get() = field
        set(value) {
          field="$field$value"
        }


}

fun main() {
    var ti=TestInterfaceImp()
    ti.data2="哈哈"
    println("${ti.data1}")
    println("${ti.data2}")
    ti.data2="嘻嘻"
    println("${ti.data1}")
    println("${ti.data2}")
}