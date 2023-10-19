package com.example.jetbackcompose.repo.sources

import com.example.jetbackcompose.Constants
import com.example.jetbackcompose.api.WebServices
import com.example.newsapp.model.Sources

class SourcesOnlineDataSourceImpl(private val webServices: WebServices) : SourcesOnlineDataSource {
    override suspend fun getSourcesFromApi(sourceId: String): List<Sources> {
        try {

            return webServices.getSources(Constants.API_KEY, sourceId).sources ?: listOf()
        } catch (ex: Exception) {
            throw ex
        }
    }
}