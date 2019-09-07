package com.turskyi.retrofitforgit

import com.google.gson.annotations.SerializedName

data class GitHubRepo(
    @SerializedName("html_url") val site: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val desc: String?
)
