package com.example.learnkt.jetpack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivityMVVM : AppCompatActivity() {


    private var mainViewModel: MainViewModel? = null
    private var mainViewModel2: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel2 = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel2?.mainListData?.observe(this, Observer {
            it.forEach { i ->
                Log.d("test2", "it=》$i")
            }
        })
        mainViewModel?.mainListData?.observe(this, Observer {
            it.forEach { i ->
                Log.d("test1", "it=》$i")
            }
        })
        mainViewModel?.getData()
    }

}