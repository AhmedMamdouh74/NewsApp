package com.example.jetbackcompose.repo.news


import com.example.jetbackcompose.api.model.ArticlesItem
import com.example.jetbackcompose.repo.NetworkHandler

class NewsRepositoryImpl(
    private val newsOfflineDataSource: NewsOfflineDataSource,
    private val newsOnlineDataSource: NewsOnlineDataSource,
    private val networkHandler: NetworkHandler
) : NewsRepository {
    override suspend fun getNewsData(sourceId: String): List<ArticlesItem> {
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