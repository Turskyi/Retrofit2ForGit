package com.turskyi.retrofitforgit.data.netsource

import com.turskyi.retrofitforgit.data.entities.GitHubRepo
import retrofit2.Call
import retrofit2.http.*

interface GitHubClient {
    @GET("users/{user}/repos")
    fun reposForUser(@Path("user") user: String): Call<List<GitHubRepo>>
}
