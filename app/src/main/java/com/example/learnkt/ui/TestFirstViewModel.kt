package com.example.learnkt.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TestFirstViewModel(var index: Int) : ViewModel() {

    var ivLData: MutableLiveData<Int> = MutableLiveData()


    fun changeIvData() {
        index++;
        ivLData.postValue(index)
    }


    class VFactory(var index: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TestFirstViewModel(index) as T
        }


    }


}