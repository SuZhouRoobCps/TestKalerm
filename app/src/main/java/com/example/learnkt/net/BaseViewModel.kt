package com.example.learnkt.net

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

open class BaseViewModel : ViewModel() {
    val  loading=MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Throwable>()

    fun launchData(
        method: suspend () -> Unit,
        error: suspend (Throwable) -> Unit,
        complete: suspend () -> Unit
    ) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                method()
            } catch (e: Exception) {
                error(e)
            } finally {
                complete()
            }
        }

    }


}