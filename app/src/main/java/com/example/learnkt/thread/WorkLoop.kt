package com.example.learnkt.thread

import java.util.*
import java.util.concurrent.PriorityBlockingQueue

class WorkLoop(val queen: PriorityBlockingQueue<WorkThread>) : Thread() {


    var currentRunningJob: WorkThread? = null

    fun addWork(worker: WorkThread) {
        if (currentRunningJob == null) {
            queen.add(worker)
        } else if (worker.getCurrentPriority()>currentRunningJob?.getCurrentPriority()?:0) {
            currentRunningJob?.interrupt()
            queen.add(worker)
            queen.add(currentRunningJob)
        }
    }


    override fun run() {
        super.run()
        while (!isInterrupted) {
            currentRunningJob = queen.take()
            currentRunningJob?.let {
                start()
                join()
            }
            currentRunningJob = null
            sleep(10)
        }
    }
}