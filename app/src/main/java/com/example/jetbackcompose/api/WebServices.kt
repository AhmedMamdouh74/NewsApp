package com.example.jetbackcompose.api


import com.example.jetbackcompose.api.model.NewsResponse
import com.example.newsapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//  The interface is used to make network requests to the news API.
interface WebServices {
    @GET("top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): Call<SourcesResponse>

    @GET("everything")
    fun getNewsBySource(
        @Query("apiKey") apiKey: String,
        @Query("sources") sourcesId: String
    ): Call<NewsResponse>
}
