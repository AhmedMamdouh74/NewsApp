package com.example.data.sources

import com.example.data.database.SourcesDao
import com.example.data.models.SourcesItem
import com.example.data.models.convertTo
import com.example.domin.entities.SourcesItemDTO
import com.example.domin.repos.SourcesOfflineDataSource


class SourcesOfflineDataSourceImpl(private val sourcesDao: SourcesDao): SourcesOfflineDataSource {
    override suspend fun getSourcesFromDB(): List<SourcesItemDTO> {
        try {
            return sourcesDao.getSourcesFromDB().map {
                it.convertTo(SourcesItemDTO::class.java)
            }
        }catch (ex:Exception){
            throw ex
        }

    }

    override suspend fun saveSourcesToDB(sourcesList: List<SourcesItemDTO>) {
        try {
            sourcesDao.saveSourcesToDB(sourcesList.map { it.convertTo(SourcesItem::class.java) })
        }catch (ex:Exception){
            throw ex
        }
    }
}