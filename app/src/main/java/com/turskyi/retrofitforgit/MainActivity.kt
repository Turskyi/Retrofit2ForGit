package com.turskyi.retrofitforgit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
       var API_BASE_URL = "https://api.github.com/"
    }

    lateinit var recyclerView: RecyclerView
    lateinit var repos: ArrayList<GitHubRepo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repos = ArrayList()
        //TODO: pagination :)
        recyclerView = findViewById(R.id.pagination_list)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = GitHubRepoAdapter(repos)
        recyclerView.adapter = adapter

        val builder: retrofit2.Retrofit.Builder = retrofit2.Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )

        val retrofit: retrofit2.Retrofit = builder.build()
        val client = retrofit.create(GitHubClient::class.java)
        val call: Call<List<GitHubRepo>> = client.reposForUser("Turskyi")
        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(object : Callback<List<GitHubRepo>> {
            override fun onResponse(
                call: Call<List<GitHubRepo>>,
                response: Response<List<GitHubRepo>>
            ) {
                // The network call was a success and we got a response
                response.body()?.let {
                    repos.addAll(it)
                }
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {
                // the network call was a failure
                Toast.makeText(applicationContext, "error :(", Toast.LENGTH_LONG).show()
            }
        })
    }
}
