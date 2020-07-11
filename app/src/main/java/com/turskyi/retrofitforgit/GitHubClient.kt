package com.turskyi.retrofitforgit

import retrofit2.Call
import retrofit2.http.*

interface GitHubClient {
    @GET("users/{user}/repos")
    fun reposForUser(@Path("user") user: String): Call<List<GitHubRepo>>
}
