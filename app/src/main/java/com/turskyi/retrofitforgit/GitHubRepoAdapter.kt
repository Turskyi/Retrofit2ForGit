package com.turskyi.retrofitforgit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_item.view.*

class GitHubRepoAdapter: RecyclerView.Adapter<GitHubRepoAdapter.ViewHolder>() {

    private val repos: MutableList<GitHubRepo> = mutableListOf()
    fun setData(repos: MutableList<GitHubRepo>) {
        this.repos.clear()
        this.repos.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context).inflate(R.layout.post_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPost = repos[position]
        holder.projectName.text = currentPost.name
        holder.site.text = currentPost.site
        holder.description.text = currentPost.desc
    }

    override fun getItemCount() = repos.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var projectName: TextView = itemView.postItemName
        var site: TextView = itemView.postItemSite
        var description: TextView = itemView.postItemDescription
    }
}

