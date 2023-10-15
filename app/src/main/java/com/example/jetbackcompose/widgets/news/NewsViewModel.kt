package com.example.jetbackcompose.widgets.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetbackcompose.Constants
import com.example.jetbackcompose.api.ApiManager
import com.example.jetbackcompose.api.model.ArticlesItem
import com.example.jetbackcompose.api.model.NewsResponse
import com.example.newsapp.model.Sources
import com.example.newsapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel:ViewModel() {
    val sourcesList =
        mutableStateOf<List<Sources>>((listOf()))

    val newsList =
        mutableStateOf<List<ArticlesItem>?>(listOf())
    var selectedIndex =
        mutableIntStateOf(0)


    fun getNewsBySource(sources: Sources, newsResponseState: MutableState<List<ArticlesItem>?>) {
        ApiManager
            .getApis()
            .getNewsBySource(Constants.API_KEY, sources.id ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    val newsResponse = response.body()
                    newsResponseState.value = newsResponse?.articles
                    Log.e("TAG", "statusResponse: ${newsResponse?.status}")
                    Log.e("TAG", "onNewsResponse: ${newsResponse?.articles}")

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                }
            })
    }

    fun getNewsSources(category: String?, sourcesList: MutableState<List<Sources>>) {
        ApiManager
            .getApis()
            .getSources(Constants.API_KEY, category = category ?: "")
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    val body = response.body()
                    Log.e("TAG", "onResponse: ${body?.status}")
                    Log.e("TAG", "onResponse: ${body?.sources}")
                    sourcesList.value = body?.sources ?: listOf()
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {


                }
            })
    }
}
