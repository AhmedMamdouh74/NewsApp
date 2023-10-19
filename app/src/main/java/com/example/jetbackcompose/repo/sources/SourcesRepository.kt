package com.example.jetbackcompose.repo.sources

import com.example.newsapp.model.Sources

interface SourcesRepository {
    suspend fun getSourcesData(sourceId:String):List<Sources>
}
interface SourcesOnlineDataSource {
    suspend fun getSourcesFromApi(sourceId: String):List<Sources>
}
interface SourcesOfflineDataSource{
    suspend fun getSourcesFromDB():List<Sources>
    suspend fun saveSourcesToDB(sourcesList: List<Sources>)
}