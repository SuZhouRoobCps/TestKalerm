package com.example.learnkt.inputHelper

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learnkt.R
import kotlinx.android.synthetic.main.activity_config_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

class ConfigActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_main)
        checkIsPermissionOK()
        start_or_stop_service.text = if (WindowManagerHelper.isShowingWindow()) "关闭悬浮窗" else "打开悬浮窗"
        start_or_stop_service.setOnClickListener {
            if(!isAssistOpen){
                Toast.makeText(this,"辅助工具未打开",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (!WindowManagerHelper.isShowingWindow()) {
                WindowManagerHelper.addWindow(this)
                start_or_stop_service.text = "关闭悬浮窗"
            } else {
                WindowManagerHelper.removeWindow()
                start_or_stop_service.text = "打开悬浮窗"
            }

        }
        tb_set.setOnClickListener {
            var b=if(randomMS.text.toString()=="") 0L else randomMS.text.toString().toLong()
            var hStr = startTime_h.text
            var mStr = startTime_m.text
            val todayEndingTimeYMD = getTodayEndingTimeYMD("$hStr:$mStr")
            limitData=todayEndingTimeYMD
            jiesuanFlag=false
            submitFlag=false
            loopFlag = false
            correctData=b
            type=1
            Log.d("test", "$todayEndingTimeYMD")
            WindowManagerHelper.upDateTvContent("时间已设置=》$hStr:$mStr",false)
        }
        go_tb_test.setOnClickListener {
            TestPageActivity.doStartIntent(this)
        }
        hema_set.setOnClickListener {
            type=2
            submitFlag=false
            hemaFlag1=true
            hemaFalg2=true
            hemaFalg3=true
            myIdentifyName=in_name.text.toString()
            myIdentifyIds=in_id.text.toString()
            myPhone=in_phone.text.toString()

            WindowManagerHelper.upDateTvContent("盒马已设置",false)
        }
    }


    override fun onRestart() {
        super.onRestart()
        checkIsPermissionOK(false)
        start_or_stop_service.text = if (WindowManagerHelper.isShowingWindow()) "关闭悬浮窗" else "打开悬浮窗"
    }


    fun checkIsPermissionOK(needJump: Boolean = true): Unit {

        if (isAccessibilitySettingOn(
                this,
                MyAccessibilityService::class.qualifiedName

            )
        ) {
            isAssistOpen=true
            helper_status.text = "沙雕辅助已经打开了"
            Toast.makeText(this, "已经打开", Toast.LENGTH_SHORT).show()
        } else {
            isAssistOpen=false
            if (needJump) {
                jumpToSettingPage(this)
            }
            helper_status.text = "需要去打开辅助功能"
        }


    }

    fun getTodayEndingTimeYMD(str: String): Long {
        val formatStr = "yyyy-MM-dd"
        val date = Date()
        val simpleDateFormat = SimpleDateFormat(formatStr)
        val format = simpleDateFormat.format(date)
        Log.d("test", format)
        Log.d("test","$format $str:00")
        val parse = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("$format $str:00")
        return parse.time
    }


}