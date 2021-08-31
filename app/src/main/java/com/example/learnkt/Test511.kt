package com.example.learnkt

import android.content.Context
import android.content.SharedPreferences
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan

fun SharedPreferences.save(
    commit: Boolean,
    context: Context,
    action: SharedPreferences.Editor.() -> Unit
) {

    val editor = edit()
//    editor.action()
    action(editor)
    if (commit) {
        editor.commit()
    } else {
        editor.apply()
    }
}


fun saveData(context: Context) {
    context.getSharedPreferences("xx", 0).save(true, context) {
        putBoolean("xxx", true)
    }
}

fun ktxSpan(s: CharSequence = SpannableString(""), func: CharSequence.() -> SpannableString) =
        s.func()
//    func(s)




private fun span(s: CharSequence, o: Any): SpannableString = when (s) {
    is SpannableString -> s
    else -> SpannableString(s)
}.apply {
    setSpan(o, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}

fun CharSequence.bold(s: CharSequence = this) = span(s, StyleSpan(android.graphics.Typeface.BOLD))
fun CharSequence.italic(s: CharSequence = this) =
    span(s, StyleSpan(android.graphics.Typeface.ITALIC))


fun main() {
  ktxSpan {
      "xxx".bold()

  }

}