package com.example.jetbackcompose.repo.sources

import com.example.jetbackcompose.database.SourcesDao
import com.example.newsapp.model.Sources

class SourcesOfflineDataSourceImpl(private val sourcesDao: SourcesDao):SourcesOfflineDataSource {
    override suspend fun getSourcesFromDB(): List<Sources> {
        try {
            return sourcesDao.getSourcesFromDB()
        }catch (ex:Exception){
            throw ex
        }

    }

    override suspend fun saveSourcesToDB(sourcesList: List<Sources>) {
        try {
            sourcesDao.saveSourcesToDB(sourcesList)
        }catch (ex:Exception){
            throw ex
        }
    }
}