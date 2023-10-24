package com.example.domin.repos

import com.example.domin.entities.ArticlesItemDTO

interface NewsRepository {
    suspend fun getNewsData(sourceId:String):List<ArticlesItemDTO>
}
interface NewsOnlineDataSource {
    suspend fun getNewsFromApi(sourceId:String):List<ArticlesItemDTO>
}interface NewsOfflineDataSource {
    suspend fun getNewsFromDB():List<ArticlesItemDTO>
    suspend fun SaveNewsToDB(newsList: List<ArticlesItemDTO>)
}
