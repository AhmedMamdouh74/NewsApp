package com.example.data.sources

import com.example.data.Constants
import com.example.data.api.WebServices
import com.example.domin.entities.SourcesItemDTO
import com.example.domin.repos.SourcesOnlineDataSource


class SourcesOnlineDataSourceImpl(private val webServices: WebServices) : SourcesOnlineDataSource {
    override suspend fun getSourcesFromApi(sourceId: String): List<SourcesItemDTO> {
        try {

            return webServices.getSources(Constants.API_KEY, sourceId).sources ?: listOf()
        } catch (ex: Exception) {
            throw ex
        }
    }
}