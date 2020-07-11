package com.turskyi.retrofitforgit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Description
 * The app shows a list of open projects on GitHub
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        var API_BASE_URL = "https://api.github.com/"
    }

    lateinit var adapter: GitHubRepoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }

    private fun loadData() {
        val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        val retrofitClient: Retrofit = builder.build()
        val gitHubClient = retrofitClient.create(GitHubClient::class.java)

        val call: Call<List<GitHubRepo>> = gitHubClient.reposForUser("Turskyi")
        call.enqueue(object : Callback<List<GitHubRepo>> {
            override fun onResponse(
                call: Call<List<GitHubRepo>>,
                response: Response<List<GitHubRepo>>
            ) { response.body()?.let {projectList ->
                    initView(projectList as MutableList<GitHubRepo>)
                }
            }
            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {
                /* the network call was a failure.  */
                Toast.makeText(this@MainActivity, "network error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initView(repos: MutableList<GitHubRepo>) {
        adapter = GitHubRepoAdapter()
        adapter.setData(repos)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
