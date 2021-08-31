package com.example.learnkt.inputHelper

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.util.Log
import android.view.*
import android.view.WindowManager.LayoutParams
import android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.learnkt.R

class WindowManagerHelper {

    companion object {

        private var mWindowManager: WindowManager? = null
        private var mLayout: LayoutParams? = null
        private var mAddedView: View? = null
        private var float_view_tv: TextView? = null
        private var float_view_time:TextView?=null

        fun addWindow(context: Context) {
            if (mWindowManager != null && mAddedView != null) {
                mWindowManager?.removeView(mAddedView)
            }
            mWindowManager =
                context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            mLayout = LayoutParams(
            )
            mLayout?.apply {
                x=0
                y=0
                width=500
                height=500
                alpha=0.5f
                gravity=Gravity.TOP
                format=PixelFormat.RGBA_8888
                flags=LayoutParams.FLAG_NOT_FOCUSABLE
                type=LayoutParams.TYPE_APPLICATION_OVERLAY
            }

            Log.d("test","当前的屏幕宽度  ${mWindowManager?.defaultDisplay?.width}")
            val screenWith=mWindowManager!!.defaultDisplay!!.width/2



            mAddedView =
                LayoutInflater.from(context).inflate(R.layout.view_item_float_view, null)
            mAddedView?.setBackgroundResource(R.drawable.float_view_border_bg)
            float_view_tv = mAddedView?.findViewById(R.id.float_view_tv)
            float_view_time= mAddedView?.findViewById(R.id.float_view_time)
            mWindowManager?.addView(mAddedView, mLayout)

            mAddedView?.setOnTouchListener { v, event ->
                val rawX = event.rawX
                val rawY = event.rawY
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> {
                        mLayout?.x = rawX.toInt()-screenWith
//                        - mAddedView!!.width/2
                        mLayout?.y = rawY.toInt()
//                        - mAddedView!!.height
                    }

                }
                mWindowManager?.updateViewLayout(mAddedView, mLayout)
                false
            }

        }


        fun upDateTimeContent(content: String){
            if(!isShowingWindow()){
                return
            }
            float_view_time?.text = content
        }

        fun upDateTvContent(content: String, append: Boolean = true) {
            if(!isShowingWindow()){
                return
            }

            var sb = StringBuffer()
            if (append) {
                sb.append(float_view_tv?.text ?: "")
                sb.append("\n")
            }
            sb.append(content)
            sb.append("\n")
            if(!isAssistOpen){
               sb.append("辅助工具未打开")
            }
            float_view_tv?.text = sb.toString()
        }

        fun removeWindow() {
            mWindowManager?.removeView(mAddedView)
            mWindowManager = null
            mAddedView = null
            float_view_tv = null
        }


        fun isShowingWindow() = mWindowManager != null

    }
}


