package com.example.learnkt

import java.util.*

class TestListener {

    interface listener{
        fun doClick(position:Int)
    }

    fun setListener(l:listener){

    }

    fun setListener(){

    }

    fun doTest(){
        setListener(object :listener{
            override fun doClick(position: Int) {
                TODO("Not yet implemented")
            }

        })

    }


}