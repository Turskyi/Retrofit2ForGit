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
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        var API_BASE_URL = "https://api.github.com/"
    }

    var repos: ArrayList<GitHubRepo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set up the view
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = GitHubRepoAdapter(repos)
        recyclerView.adapter = adapter

        // set up the retrofit
        val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofitClient: Retrofit = builder.build()

        val gitHubClient = retrofitClient.create(GitHubClient::class.java)

        val call: Call<List<GitHubRepo>> = gitHubClient.reposForUser("Turskyi")
        call.enqueue(object : Callback<List<GitHubRepo>> {

            override fun onResponse(
                call: Call<List<GitHubRepo>>,
                response: Response<List<GitHubRepo>>
            ) {
                response.body()?.let {
                    repos.addAll(it)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {
                // the network call was a failure
                Toast.makeText(applicationContext, "error :(", Toast.LENGTH_LONG).show()
            }
        })
    }
}
