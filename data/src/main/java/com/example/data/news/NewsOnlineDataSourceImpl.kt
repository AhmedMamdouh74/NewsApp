package com.example.data.news
import com.example.data.Constants
import com.example.data.api.WebServices
import com.example.domin.entities.ArticlesItemDTO
import com.example.domin.repos.NewsOnlineDataSource


class NewsOnlineDataSourceImpl(private val webServices: WebServices) : NewsOnlineDataSource {
    override suspend fun getNewsFromApi(sourceId: String): List<ArticlesItemDTO> {
        try {
            return webServices.getNewsBySource(Constants.API_KEY, sourceId).articles ?: listOf()
        } catch (ex: Exception) {
            throw ex
        }
    }
}