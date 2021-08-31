package com.example.learnkt.inputHelper

import android.accessibilityservice.AccessibilityService
import android.app.ActivityManager
import android.app.Service
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.coroutines.CoroutineContext

/**
 * 页面监听
 *
 * 同意授权，提交实名认证信息
 *
 *  android.widget.TextView com.taobao.taobao:id/button_cart_charge  结算(1)
 * // boundsInScreen  Rect(354, 368 - 893, 440)  l  t   r  b   354/368/893/440   name
 * // boundsInScreen  Rect(354, 497 - 893, 569)                354/497/893/569    id
 * //                 Rect(277, 528 - 998, 599)                277/528/998/599    phone
 *
 * 35/981/88/1039   check box
 *
 */
class MyAccessibilityService : AccessibilityService() {

    var nodeMapData: HashMap<String, AccessibilityNodeInfo> = HashMap()  // String => View
    var nodeHemaData: HashMap<String, AccessibilityNodeInfo> =
        HashMap() //String boundsInScreen =》View
    var lastPageName: String = ""
    var startTime = 0L


    val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> WindowManagerHelper.upDateTimeContent("${msg.obj.toString()}  $jiesuanFlag 提交")
            }
        }
    }

    override fun onInterrupt() {


    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (!WindowManagerHelper.isShowingWindow()) {
            return
        }
        startTime = limitData
//        Log.d("test", "MyAccessibilityService$startTime $correctData $type")
        val currentPageName = getCurrentPageName(applicationContext)
        lastPageName = currentPageName
//        WindowManagerHelper.upDateTvContent(currentPageName, false)
//       Log.d("test","当前包类型${event?.packageName.toString()}")
//        Log.d("test","当前输入类型${event?.eventType.toString()}")


        val rootNodeInfo: AccessibilityNodeInfo? = rootInActiveWindow
        if (rootNodeInfo == null) {
            Log.d("test", "KONGKONG空空")
            return
        }
        nodeMapData?.clear()
        logNodeInfo(rootNodeInfo)
//        showNodeInfo()
        when (type) {
            1 -> doTB()
            2 -> doHeMa(
                "354/368/893/440",
                "354/497/893/569",
                "277/528/998/599"
            )
        }

    }

    fun logNodeInfo(rootNode: AccessibilityNodeInfo) {
        if (rootNode.childCount > 0) {
            for (i in 0 until rootNode.childCount) {
                val currentNodeInfo = rootNode.getChild(i)

//                Log.d(
//                    "test",
//                    "${currentNodeInfo?.className} ${currentNodeInfo?.viewIdResourceName}  ${currentNodeInfo?.text}"
//                )
                if (currentNodeInfo == null) {
                    continue
                }

//                currentNodeInfo.findAccessibilityNodeInfosByViewId()
                if (currentNodeInfo.text != null) {
                    nodeMapData?.put(
                        currentNodeInfo?.text.toString(),
                        currentNodeInfo
                    )
                }
                if (currentNodeInfo.text != null && currentNodeInfo.text.toString()
                        .contains("提交实名认证信息")
                ) {
                    nodeHemaData["提交实名认证信息"] = currentNodeInfo
                } else if(currentNodeInfo.text!=null&&currentNodeInfo.text.toString()=="请选择"){
                    nodeHemaData["请选择"]=currentNodeInfo
                }else {
                    var rect = Rect()
                    currentNodeInfo.getBoundsInScreen(rect)
                    rect.let {
                        nodeHemaData.put(
                            "${rect.left}/${rect.top}/${rect.right}/${rect.bottom}",
                            currentNodeInfo
                        )
                    }

                    if (currentNodeInfo.childCount > 0) {
                        logNodeInfo(currentNodeInfo)
                    }
                }
            }
        }


    }

    fun showNodeInfo() {
        nodeMapData?.forEach { k, v ->
            Log.d("test", "$k -> ${v.toString()}")

        }
        nodeHemaData.forEach { k, v ->
            Log.d("test", "$k -> ${v.toString()}")
        }

    }


    fun getCurrentPageName(context: Context): String {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val getInfo = am.getRunningTasks(1)[0].topActivity

        return getInfo?.className ?: ""
    }


    fun checkCurrentType() {
        when (type) {


        }


    }

    /**
     * 盒马生鲜的干活
     *  现在先假设
     *
     *
    //  请输入姓名  请输入身份证号  请输入您的手机号
     *
     */
    fun doHeMa(name: String, id: String, phone: String) {
        loopFlag = false
        if (!WindowManagerHelper.isShowingWindow()) {
            return
        }

        if (nodeHemaData[name]?.text.toString() == myIdentifyName
            && nodeHemaData[id]?.text.toString() == myIdentifyIds
            && nodeHemaData["提交实名认证信息"] != null&& hemaFlag1
        ) {
            nodeMapData["提交实名认证信息"]?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            hemaFlag1=false
        }



        if (nodeHemaData[name] != null
            && nodeHemaData[name]?.className == "android.widget.EditText"
            && nodeHemaData[name]?.text.toString() != myIdentifyName
        ) {
            doTestText(nodeHemaData[name]!!, myIdentifyName)
        }
        if (nodeHemaData[id] != null
            && nodeHemaData[id]?.className == "android.widget.EditText"
            && nodeHemaData[id]?.text.toString() != myIdentifyIds
        ) {
            doTestText(nodeHemaData[id]!!, myIdentifyIds)
        }
        if (nodeHemaData[phone] != null
            && nodeHemaData[phone]?.className == "android.widget.EditText"
            && nodeHemaData[phone]?.text.toString() != myPhone
        ) {
            doTestText(nodeHemaData[phone]!!, myPhone)
        }
        if(nodeHemaData["35/981/88/1039"]!=null
            &&nodeHemaData["35/981/88/1039"]?.className=="android.widget.CheckBox"
            &&nodeHemaData["35/981/88/1039"]?.isChecked==false
        ){
            nodeHemaData["35/981/88/1039"]?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
        }

    }


    fun doTB() {
        if (!WindowManagerHelper.isShowingWindow()) {
            return
        }
        if (loopFlag && nodeMapData["结算(1)"] != null) {
            return
        }
        Log.d("test", "干淘宝")
        if (nodeMapData["提交订单"] != null && !submitFlag) {
            WindowManagerHelper.upDateTvContent("提交订单 已锁定", false)
            submitFlag = true
            loopFlag = false
            nodeMapData["提交订单"]?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            Log.i("test", "点击点击提交")
        } else if (nodeMapData["结算(1)"] != null && !loopFlag && !jiesuanFlag) {
            WindowManagerHelper.upDateTvContent("结算(1) 已锁定", false)
            loopFlag = true
            Log.d("test", "开起来新线程")
            Thread(Runnable {
                while (loopFlag && nodeMapData["结算(1)"] != null) {
                    sleep(100)
                    var message = Message.obtain()
                    message.what = 1
                    message.obj = "${limitData - System.currentTimeMillis() - correctData}"
                    handler.sendMessage(message)
                    if (limitData - System.currentTimeMillis() < correctData) {
                        jiesuanFlag = true
                        val performAction =
                            nodeMapData["结算(1)"]?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        Log.i("test", "点击点击结算")
//                        WindowManagerHelper.upDateTvContent("结算点击成功$performAction", false)
                        loopFlag = false
                    }

                }
                loopFlag = false
            }).start()

        } else {
            loopFlag = false
            WindowManagerHelper.upDateTvContent("没招到", false)

        }

    }

//    private void changeInput(AccessibilityNodeInfo info,String text) {  //改变editText的内容
//        Bundle arguments = new Bundle();
//        arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
//            text);
//        info.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
//    }

    fun doTestText(info: AccessibilityNodeInfo, text: String) {
        var arguments = Bundle()
        arguments.putCharSequence(
            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
            text
        )
        info.performAction(AccessibilityNodeInfo.ACTION_FOCUS)
        val performAction = info.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments)
        Log.e("test", "设置结果$performAction")
    }

    /**
     *   ctx = getApplicationContext();
    ClipboardManager clipboard = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
    ClipData clip = ClipData.newPlainText("label", "XYZ");
    clipboard.setPrimaryClip(clip);
    source.performAction(AccessibilityNodeInfo.ACTION_PASTE);
     */
    fun doTestText2(info: AccessibilityNodeInfo, text: String, index: Int) {
        val clipBoard =
            applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clip = ClipData.newPlainText("label", text)
        clipBoard.setPrimaryClip(clip)
        var bundle = Bundle()
        bundle.putInt(AccessibilityNodeInfo.ACTION_ARGUMENT_SELECTION_START_INT, 0)
        bundle.putInt(AccessibilityNodeInfo.ACTION_ARGUMENT_SELECTION_END_INT, text.length)
        info.performAction(AccessibilityNodeInfo.ACTION_FOCUS)
        info.performAction(AccessibilityNodeInfo.ACTION_SET_SELECTION, bundle)
        val result = !info.performAction(AccessibilityNodeInfo.ACTION_PASTE)
        Log.e("test", "输入结果$result")

    }

    fun doTest3(info: AccessibilityNodeInfo, text: String) {
//        if(info.className){
//
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loopFlag = false
    }
}
