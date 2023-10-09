package com.example.jetbackcompose.api

import com.example.jetbackcompose.api.model.Category
import com.example.newsapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//  The interface is used to make network requests to the news API.
interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): Call<SourcesResponse>
}
