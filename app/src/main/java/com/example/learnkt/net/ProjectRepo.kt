package com.example.learnkt.net

class ProjectRepo :BaseRepository(){
    private lateinit var mService:ProjectApi
    init {
        mService=RetrofitManager.initRetrofit().getService(ProjectApi::class.java)
    }

    suspend fun loadProjectTree():ResState<List<ProjectTree>>{
        return executeResp(mService.loadProjectTree())
    }
}