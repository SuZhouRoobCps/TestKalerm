package com.example.learnkt.inputHelper

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.text.TextUtils
import android.util.Log

fun isAccessibilitySettingOn(context: Context, className: String?): Boolean {
    if(TextUtils.isEmpty(className)){
        return false
    }
    val systemService = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningServices = systemService?.getRunningServices(10)
    if (runningServices == null || runningServices.size == 0) {
        return false
    }
    for (service in runningServices) {
        if (service.service.className == className) {
            Log.d("test","${service.service.className} ===== $className")
            return true
        }
    }
    return false
}

fun jumpToSettingPage(context: Context): Unit {
    var intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}