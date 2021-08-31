package com.example.learnkt

import android.app.UiAutomation
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TestAdapter : RecyclerView.Adapter<TestAdapter.MViewHolder>() {


    inner class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        if (listener != null) {
            holder.tv?.setOnClickListener {
                listener!!(position, position.toString())

            }
        }
    }

    private var listener: ((Int, String) -> Unit)? = null

    fun setMListener(ls: (Int, String) -> Unit) {
        this.listener = ls
    }

}