package com.turskyi.retrofitforgit.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.turskyi.retrofitforgit.data.entities.GitHubRepo
import com.turskyi.retrofitforgit.databinding.PostItemBinding

class GitHubRepoAdapter: RecyclerView.Adapter<GitHubRepoAdapter.ViewHolder>() {

    private val repos: MutableList<GitHubRepo> = mutableListOf()
    fun setData(repos: MutableList<GitHubRepo>) {
        this.repos.clear()
        this.repos.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PostItemBinding = PostItemBinding.inflate(LayoutInflater
            .from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPost = repos[position]
        holder.projectName.text = currentPost.name
        holder.site.text = currentPost.site
        holder.description.text = currentPost.desc
    }

    override fun getItemCount() = repos.size

    inner class ViewHolder(binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var projectName: TextView = binding.postItemName
        var site: TextView = binding.postItemSite
        var description: TextView = binding.postItemDescription
    }
}

