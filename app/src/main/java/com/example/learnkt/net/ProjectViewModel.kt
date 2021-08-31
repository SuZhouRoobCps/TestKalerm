package com.example.learnkt.net

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProjectViewModel : BaseViewModel() {
    val mProjectLiveData = MutableLiveData<List<ProjectTree>>()
    val projectRepo = ProjectRepo()
//    val errorData=MutableLiveData<Throwable>()
//        fun loadProjectTree(){
//            viewModelScope.launch(Dispatchers.IO) {
//                try{
//                    val data = ProjectRepo().loadProjectTree()
////                mProjectLiveData.postValue(data)
//                }catch (e:Exception){
//                    errorData.postValue(e)
//                }finally {
//
//                }
//            }
//        }

    fun loadProjectTree() {
        launchData(
            {
                loading.postValue(true)
                val backData = projectRepo.loadProjectTree()
                if(backData is ResState.Success){
                    mProjectLiveData.postValue(backData.data)
                }else if(backData is ResState.Error){
                    errorLiveData.postValue(backData.exception)
                }
            },
            { it ->
                errorLiveData.postValue(it)

            }, {
                loading.postValue(false)
            }
        )
    }


}