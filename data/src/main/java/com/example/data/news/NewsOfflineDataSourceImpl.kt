package com.example.data.news

import com.example.data.database.NewsDao
import com.example.data.models.ArticlesItem
import com.example.data.models.convertTo
import com.example.domin.entities.ArticlesItemDTO
import com.example.domin.repos.NewsOfflineDataSource


class NewsOfflineDataSourceImpl(val newsDao: NewsDao) : NewsOfflineDataSource {
    override suspend fun getNewsFromDB(): List<ArticlesItemDTO> {
        try {

            return newsDao.getNewsFromDB().map {
                it.convertTo(ArticlesItemDTO::class.java)
            }
        } catch (ex: Exception) {
            throw ex
        }
    }


    override suspend fun SaveNewsToDB(newsList: List<ArticlesItemDTO>) {
        try {
            newsDao.insertNewsToDB(newsList.map {
                it.convertTo(ArticlesItem::class.java)
            })
        } catch (ex: Exception) {
            throw ex
        }


    }
}