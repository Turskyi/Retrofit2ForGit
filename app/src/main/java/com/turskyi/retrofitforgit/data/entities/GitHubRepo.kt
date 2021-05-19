package com.turskyi.retrofitforgit.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class GitHubRepo(
    var owner: User? = null,
    @SerializedName("html_url") val site: String?,
    @SerializedName("git_url") val gitUrl: String?,
    @SerializedName("ssh_url") val sshUrl: String?,
    @SerializedName("clone_url") val cloneUrl: String?,
    @SerializedName("svn_url") val svnUrl: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("created_at") val dateCreated: Date?,
    @SerializedName("updated_at") val dateUpdated: Date?,
    @SerializedName("pushed_at") val datePushed: Date?,
    @SerializedName("description") val desc: String?
)
