package com.example.learnkt.weituo

class LogList(
    val log: () -> Unit,
    val list: MutableList<String>
) : MutableList<String> by list {
    fun getAndLog(index: Int): String {
        log()
        return get(index)
    }

}