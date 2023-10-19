package com.example.jetbackcompose.repo.news
import com.example.jetbackcompose.Constants
import com.example.jetbackcompose.api.WebServices
import com.example.jetbackcompose.api.model.ArticlesItem

class NewsOnlineDataSourceImpl(private val webServices: WebServices) : NewsOnlineDataSource {
    override suspend fun getNewsFromApi(sourceId: String): List<ArticlesItem> {
        try {
            return webServices.getNewsBySource(Constants.API_KEY, sourceId).articles ?: listOf()
        } catch (ex: Exception) {
            throw ex
        }
    }
}