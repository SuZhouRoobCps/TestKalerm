package com.example.learnkt.ui

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Camera
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learnkt.R
import kotlinx.android.synthetic.main.activity_test_first.*
import kotlinx.android.synthetic.main.activity_test_second.*
import kotlinx.android.synthetic.main.layout_loading_view.*

class TestSecondActivity : AppCompatActivity() {


    val index: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_second)

        val vm = ViewModelProvider(
            this,
            TestFirstViewModel.VFactory(index)
        ).get(TestFirstViewModel::class.java)
        vm.ivLData.observe(this, Observer {
            Log.d("test","第二个页面$it")
        })


        sec_change.setOnClickListener {
            vm.changeIvData()

        }
    }


}