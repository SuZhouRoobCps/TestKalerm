package com.example.learnkt

import android.graphics.Rect
import android.view.MotionEvent
import android.view.TouchDelegate
import android.view.View

class MultiTouchDelegate(bounds: Rect, delegateView: View?) : TouchDelegate(bounds, delegateView) {
    //保存多个代理控件及其触摸区域容器
    val delegateViewMap = mutableMapOf<View, Rect>()

    //当前的代理控件
    private var delegateView: View? = null

    //新增代理控件
    fun addDelegateView(delegateView: View, rect: Rect) {
        delegateViewMap[delegateView] = rect
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        var handled = false
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                delegateView=findDelegateViewUnder(x,y)

            }
            MotionEvent.ACTION_CANCEL ->{
                delegateView=null
            }

        }
        //如果找到代理控件，把所有事件传递给他消费
        delegateView?.let {
            event.setLocation(it.width/2f,it.height/2f)
            handled=it.dispatchTouchEvent(event)
        }
        return handled


    }


    private fun findDelegateViewUnder(x: Int, y: Int): View? {
        delegateViewMap.forEach { entry ->
            if (entry.value.contains(x, y)) {
                return entry.key
            }
        }
        return null
    }


}