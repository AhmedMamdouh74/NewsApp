package com.example.data.sources

import com.example.data.NetworkHandler
import com.example.domin.entities.SourcesItemDTO
import com.example.domin.repos.SourcesOfflineDataSource
import com.example.domin.repos.SourcesOnlineDataSource
import com.example.domin.repos.SourcesRepository


class SourcesRepositoryImpl(
    private val sourcesOnlineDataSource: SourcesOnlineDataSource,
    private val sourcesOfflineDataSource: SourcesOfflineDataSource,
    private val networkHandler: NetworkHandler
) : SourcesRepository {
    override suspend fun getSourcesData(sourceId: String): List<SourcesItemDTO> {
        try {
            return if (networkHandler.isOnline()) {
                val sourcesList = sourcesOnlineDataSource.getSourcesFromApi(sourceId)
                sourcesOfflineDataSource.saveSourcesToDB(sourcesList)
                sourcesList
            } else
                sourcesOfflineDataSource.getSourcesFromDB()
        } catch (ex: Exception) {
            throw ex
        }
    }
}