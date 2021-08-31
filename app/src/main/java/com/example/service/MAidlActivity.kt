package com.example.service

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MAidlActivity : AppCompatActivity(), ServiceConnection {

    val scope = MainScope()
    lateinit var bookManager: BookManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scope.launch {
            doDataTest()

        }



    }

    override fun onServiceDisconnected(name: ComponentName?) {
        TODO("Not yet implemented")
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        bookManager = BookManager.Stub.asInterface(service)
    }

    fun doData() {
        bookManager.addBook(null)
    }

    suspend fun doDataTest() {
        delay(1100)
        println("xxxx")
    }

    suspend fun doDataTest2(){
        withContext(Dispatchers.IO){

        }
    }

}