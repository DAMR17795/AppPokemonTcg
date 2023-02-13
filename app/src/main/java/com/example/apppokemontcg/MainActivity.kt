package com.example.myapplication

import PokemonCardAdapter
import android.os.Bundle
import android.telecom.Call
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppokemontcg.NewsApiClient
import com.example.apppokemontcg.PokemonCard
import com.example.apppokemontcg.PokemonResponse
import com.example.apppokemontcg.R
import okhttp3.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private val newsList = arrayListOf<PokemonCard>()
    private lateinit var newsAdapter: PokemonCardAdapter
    private lateinit var rv_news:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        newsAdapter = PokemonCardAdapter(newsList)

        rv_news= findViewById<RecyclerView>(R.id.rvMain)
        rv_news.layoutManager = LinearLayoutManager(this)
        rv_news.adapter = newsAdapter

        val newsApiService = NewsApiClient.create()

        newsApiService.getTopHeadlines("us", "0449fc9bb3fc43caa89f1b1064210789").enqueue(object : Callback<PokemonResponse> {
                override fun onResponse(
                    call: Call<PokemonResponse>,
                    response: Response<PokemonResponse>
                ) {
                    if (response.isSuccessful) {
                        val news = response.body()?.articles
                        newsList.addAll(news!!)
                        newsAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error al obtener noticias", Toast.LENGTH_SHORT).show()
                }
            })
    }
}


