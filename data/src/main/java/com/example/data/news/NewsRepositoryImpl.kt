package com.example.data.news


import com.example.data.NetworkHandler
import com.example.domin.entities.ArticlesItemDTO
import com.example.domin.repos.NewsOfflineDataSource
import com.example.domin.repos.NewsOnlineDataSource
import com.example.domin.repos.NewsRepository


class NewsRepositoryImpl(
    private val newsOfflineDataSource: NewsOfflineDataSource,
    private val newsOnlineDataSource: NewsOnlineDataSource,
    private val networkHandler: NetworkHandler
) : NewsRepository {
    override suspend fun getNewsData(sourceId: String): List<ArticlesItemDTO> {
        try {


            return if (networkHandler.isOnline()) {
                val newsList = newsOnlineDataSource.getNewsFromApi(sourceId)
                newsOfflineDataSource.SaveNewsToDB(newsList)
                newsList
            } else
                newsOfflineDataSource.getNewsFromDB()
        } catch (ex: Exception) {
            throw ex
        }

    }
}