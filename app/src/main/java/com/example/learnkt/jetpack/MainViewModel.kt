package com.example.learnkt.jetpack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val mainListData = MutableLiveData<List<String>>()
    private val mainRepository=MainRepository()

    fun getData(){
        viewModelScope.launch {
           mainListData.value=mainRepository.getData()
        }

    }

}