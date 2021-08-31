package com.example.learnkt.thread

import java.util.concurrent.PriorityBlockingQueue

class WorkManager {
    var comparator = Comparator<WorkThread> { o1, o2 ->
        o1.getCurrentPriority() - o2.getCurrentPriority()
    }

    var workQueen = PriorityBlockingQueue<WorkThread>(10, comparator)
    private val workLoop = WorkLoop(workQueen)
    fun doRun() {
        workLoop.start()
    }
    fun addJob(Work:WorkThread){
        
    }

    companion object {
        val wManager = WorkManager()
    }


}