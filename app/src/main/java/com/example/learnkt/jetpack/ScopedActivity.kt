package com.example.learnkt.jetpack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_page.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ScopedActivity : AppCompatActivity(), CoroutineScope {

    lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun loadDataFromUI() {
        launch {
            val asyncJob = async {
                delay(1000)
                "xxxxx"
            }
            val await = asyncJob.await()
            if (await is String) {
                Log.d("test", "2222")
            }
            test_charge?.text=await
        }
    }

}