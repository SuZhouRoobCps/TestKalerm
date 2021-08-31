package com.example.learnkt.inputHelper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.learnkt.R
import kotlinx.android.synthetic.main.activity_test_page.*
import kotlinx.coroutines.*

class TestPageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_page)
        test_charge.setOnClickListener {
            println("6666666666666666666666666￥${test_charge.text}")
            test_charge.text="提交订单"
        }
//        lifecycleScope.launch {
//
//        }

    }

    companion object {

        fun doStartIntent(context: Context){
            val  intent=Intent(context,TestPageActivity::class.java)
            context.startActivity(intent)
        }

    }

    fun showData(){
        CoroutineScope(Dispatchers.Main+ Job()).launch {
            val str=getUserData()

        }
        val mainScope = MainScope()

    }


    suspend fun getUserData():String{
        withContext(Dispatchers.IO){
            delay(1000)

        }
        return ""
    }

    /**
     *  捕获异常
     */
    fun test1(){

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.d("test", throwable.message.toString())

        }

        CoroutineScope(Dispatchers.Main+coroutineExceptionHandler).launch {
                delay(1000)
                throw CancellationException("xxx")

        }


    }




}