package com.example.learnkt.thread

abstract class WorkThread(id: Int, priority: Int) : Thread() {


    override fun run() {
        super.run()

    }

    fun getCurrentId() = id
    fun getCurrentPriority() = priority

    abstract fun doStart()
    abstract fun doRun()
    abstract fun doLast()
}