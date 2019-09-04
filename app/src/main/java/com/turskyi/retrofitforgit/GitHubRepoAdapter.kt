package com.turskyi.retrofitforgit

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView

class GitHubRepoAdapter internal constructor(
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.post.text = Html.fromHtml(post.elementPureHtml, Html.FROM_HTML_MODE_LEGACY)
        } else {
            holder.post.text = post.elementPureHtml?.let {
                HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
        }
        holder.site.text = post.site
    }

    override fun getItemCount(): Int {
        return repos?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var post: TextView = itemView.findViewById(R.id.post_item_post) as TextView
        var site: TextView = itemView.findViewById(R.id.post_item_site) as TextView
    }
}

