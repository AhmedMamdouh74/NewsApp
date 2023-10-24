package com.example.domin.repos

import com.example.domin.entities.SourcesItemDTO

interface SourcesRepository {
    suspend fun getSourcesData(sourceId:String):List<SourcesItemDTO>
}
interface SourcesOnlineDataSource {
    suspend fun getSourcesFromApi(sourceId: String):List<SourcesItemDTO>
}
interface SourcesOfflineDataSource{
    suspend fun getSourcesFromDB():List<SourcesItemDTO>
    suspend fun saveSourcesToDB(sourcesList: List<SourcesItemDTO>)
}