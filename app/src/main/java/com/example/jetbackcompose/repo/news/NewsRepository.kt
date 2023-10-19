package com.example.jetbackcompose.repo.news

import com.example.jetbackcompose.api.model.ArticlesItem

interface NewsRepository {
    suspend fun getNewsData(sourceId:String):List<ArticlesItem>
}
interface NewsOnlineDataSource {
    suspend fun getNewsFromApi(sourceId:String):List<ArticlesItem>
}interface NewsOfflineDataSource {
    suspend fun getNewsFromDB():List<ArticlesItem>
    suspend fun SaveNewsToDB(newsList: List<ArticlesItem>)
}
