package com.example.learnkt.net

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.view.WindowMetrics
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.example.learnkt.R
import kotlinx.android.synthetic.main.activity_test_page.*

class LoadingDialog :Dialog {

    private var loadingDialog:LoadingDialog?=null

    constructor(context: Context) : super(context, R.style.AppTheme){
        setContentView(R.layout.layout_loading_view)
        window?.attributes?.width=200
        window?.attributes?.height=200
        val imageView:ImageView=findViewById(R.id.iv_image)
        val rotateAnimation = RotateAnimation(0f, 360f, 0.5f, 0.5f)
        rotateAnimation.repeatMode=Animation.RESTART
        rotateAnimation.repeatCount=Animation.INFINITE
        rotateAnimation.duration=2000
        rotateAnimation.fillAfter=true
        imageView.startAnimation(rotateAnimation)
    }

    fun showDialog(context: Context,cancelAble: Boolean){
        if(context is Activity){
            if(context.isFinishing){
                return
            }
        }
        if(loadingDialog==null){
            loadingDialog= LoadingDialog(context)
        }
        setCancelable(cancelAble)
        loadingDialog?.show()
    }

    fun dismissDialog(){
        loadingDialog?.dismiss()
    }

}