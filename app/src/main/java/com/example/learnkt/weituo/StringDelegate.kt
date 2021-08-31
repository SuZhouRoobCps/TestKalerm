package com.example.learnkt.weituo

import kotlin.reflect.KProperty

/**
 * 属性委托
 *
 *
 *
 */
class StringDelegate(
    private var s: String = "Hello"
) {

    operator fun getValue(thisRef: Owner, property: KProperty<*>): String {
        return s
    }

    operator fun setValue(thisRef: Owner, property: KProperty<*>, value: String) {
        s = value
    }




}