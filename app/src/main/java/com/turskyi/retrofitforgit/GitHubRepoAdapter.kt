package com.turskyi.retrofitforgit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GitHubRepoAdapter constructor(
    private val repos: List<GitHubRepo>?
) :
    RecyclerView.Adapter<GitHubRepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater
            .from(parent.context).inflate(R.layout.post_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = repos!![position]
        holder.post.text = post.name
        holder.site.text = post.site
        holder.description.text = post.desc
    }

    override fun getItemCount(): Int {
        return repos?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var post: TextView = itemView.findViewById(R.id.postItemName) as TextView
        var site: TextView = itemView.findViewById(R.id.postItemSite) as TextView
        var description: TextView = itemView.findViewById(R.id.postItemDescription) as TextView
    }
}

