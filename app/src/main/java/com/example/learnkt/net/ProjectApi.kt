package com.example.learnkt.net

import retrofit2.http.GET

interface ProjectApi {

    @GET("project/tree/json")
    suspend fun loadProjectTree(): BaseResp<List<ProjectTree>>
}