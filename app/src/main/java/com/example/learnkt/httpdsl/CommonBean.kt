package com.example.learnkt.httpdsl

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.io.Serializable

@Parcelize
class CommonBean<T>(
    var success: Boolean = false,
    var result: @RawValue T? = null,
    val error: ErrorBean? = null,
    val message: String? = ""
) : Parcelable, Serializable {
}