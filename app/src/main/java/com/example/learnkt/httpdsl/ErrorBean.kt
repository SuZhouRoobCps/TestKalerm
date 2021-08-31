package com.example.learnkt.httpdsl

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class ErrorBean(
    var errorCode: Int = -1,
    var errorKey: String? = "",
    var errorMessage: String = ""
) : Parcelable, Serializable {
}