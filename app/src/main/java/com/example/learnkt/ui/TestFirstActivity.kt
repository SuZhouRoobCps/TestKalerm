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
import kotlinx.android.synthetic.main.layout_loading_view.*

class TestFirstActivity : AppCompatActivity() {

    var index: Int = 0;
    lateinit var viewModel: TestFirstViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_first)

        viewModel = ViewModelProvider(
            this,
            TestFirstViewModel.VFactory(index)
        ).get(TestFirstViewModel::class.java)

        go_page_2.setOnClickListener {

        }

        change.setOnClickListener {
            viewModel.changeIvData()
        }
        viewModel.ivLData.observe(this, Observer {
            Log.d("test","TestFirstActivity收到了消息$it")
            roteIv()
        })
    }


    fun roteIv() {
        val ofFloat = ValueAnimator.ofFloat(0f, 360f)
        with(ofFloat) {
            repeatCount = 1
            interpolator = LinearInterpolator()
            duration = 10000
            addUpdateListener {
                Log.d("test", "currentValue${it.animatedValue}")
                page1_iv.rotationY = it.animatedValue as Float

            }
//            start()
        }

        val rotateAnimation = RotateAnimation(
            0f, 180f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        with(rotateAnimation) {
            duration = 10000
            repeatCount = 0
            interpolator = LinearInterpolator()

        }
        page1_iv.startAnimation(rotateAnimation)
    }

}