package com.example.learnkt.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.learnkt.R
import kotlinx.android.synthetic.main.test_activity.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class TestActivity : AppCompatActivity() {

    private var flag: Boolean = false
    var cJob: Job? = null
    val coroutineScope: CoroutineScope = MainScope()
    var secondTimeClick: Boolean = false;
    lateinit var liveData: MutableLiveData<String>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity)
        val coroutineScope = CoroutineScope(Dispatchers.Default)
        val mainScope = MainScope()

        mainScope.cancel()

//        val job = lifecycleScope.launch {
//            withContext(Dispatchers.IO) {
//
//            }
//
//
//        }
//        job.cancel()

        test_click.setOnClickListener {
//            lifecycleScope.launch { //模拟数据加载
//                Log.d("test",Thread.currentThread().name)
//                val loadData1 = loadData1()
//                showData1(loadData1)
//            }

//            Thread {
//                Log.d("test", Thread.currentThread().name)
//                Thread.sleep(5000)
//                Log.d("test","五秒之后")
////                Toast.makeText(this, "xxx", Toast.LENGTH_SHORT).show() //exception
//            }.start()

            //模拟双击事件处理
//            if (secondTimeClick) {
//                Toast.makeText(this, "双击事件", Toast.LENGTH_SHORT).show()
//            } else {
//                secondTimeClick = true
//                lifecycleScope.launch {
//                    resetClickFlag()
//                }
//
//            }
//
//            lifecycleScope.launch {
//                val measureTime = measureTimeMillis {
//                    val addResult = getAddResult(1, 2)
//                    Log.d("test", "结果$addResult")
//                }
//                Log.d("test", "总体耗时$measureTime")
//            }
            lifecycleScope.launch {
                val measureTimeMillis = measureTimeMillis {

                    testPostData()
                }
                Log.d("test","耗时$measureTimeMillis")
            }
        }


        liveData = MutableLiveData<String>()
        liveData.observe(this, Observer {
            Log.d("test", "收到消息$it")
        })

    }

//    private suspend fun delayResetData() {
//        withContext(Dispatchers.Main) {
//            Log.d("test", Thread.currentThread().name + " 当前线程名字")
//
//        }
//        delay(1000)
//        Log.d("test", Thread.currentThread().name + " 当前线程名字2")
//        flag = true;
//    }


    suspend fun testPostData() {
        withContext(Dispatchers.IO) {
            delay(1000)
            liveData.postValue("sssss")
        }
    }

    suspend fun loadData1(): String {
        var i: Int = 0;
        withContext(Dispatchers.IO) {
            delay(2000)
            i++;
        }
        return "$i"
    }

    fun showData1(str1: String) {
        test_click.text = str1
    }

    suspend fun resetClickFlag() {
        Log.d("test", "1")
        withContext(Dispatchers.IO) {
            delay(3000)
            Log.d("test", "2")
            secondTimeClick = false;
        }
        Log.d("test", "3")

    }

    suspend fun getAddResult(i1: Int, i2: Int): Int {
        var result = 0;
        withContext(Dispatchers.IO) {
            val async = async {
                delay(2000)
                i1
            }
            val async1 = async {

                delay(3000)
                i2
            }
            result = async.await() + async1.await()

        }
        return result


    }


}