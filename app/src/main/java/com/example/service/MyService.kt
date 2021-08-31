package com.example.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service(){

    lateinit var  booList:ArrayList<Book>;

    override fun onBind(intent: Intent?): IBinder? {
        return mBookManager
    }


    var mBookManager:BookManager.Stub=object : BookManager.Stub(){
        override fun addBook(book: Book?): Book {
            if(booList==null){
                booList=ArrayList()
            }
            book?.let {
            booList.add(book)
            }
            return book?:Book(1,"2")
        }
    }



}