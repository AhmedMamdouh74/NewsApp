package com.example.data.api


import com.example.data.models.NewsResponse
import com.example.data.models.SourcesResponse
import com.example.domin.entities.NewsResponseDTO
import com.example.domin.entities.SourcesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

//  The interface is used to make network requests to the news API.
interface WebServices {
    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): SourcesResponse

    @GET("everything")
    suspend fun getNewsBySource(
        @Query("apiKey") apiKey: String,
        @Query("sources") sourcesId: String
    ): NewsResponse
}
