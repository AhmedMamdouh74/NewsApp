package com.example.jetbackcompose.repo.sources

import com.example.jetbackcompose.repo.NetworkHandler
import com.example.newsapp.model.Sources

class SourcesRepositoryImpl(
    private val sourcesOnlineDataSource: SourcesOnlineDataSource,
    private val sourcesOfflineDataSource: SourcesOfflineDataSource,
    private val networkHandler: NetworkHandler
) : SourcesRepository {
    override suspend fun getSourcesData(sourceId: String): List<Sources> {
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