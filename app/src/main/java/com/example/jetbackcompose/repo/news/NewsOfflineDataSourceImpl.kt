package com.example.jetbackcompose.repo.news

import com.example.jetbackcompose.api.model.ArticlesItem
import com.example.jetbackcompose.database.NewsDao

class NewsOfflineDataSourceImpl(val newsDao: NewsDao) : NewsOfflineDataSource {
    override suspend fun getNewsFromDB(): List<ArticlesItem> {
        try {

            return newsDao.getNewsFromDB()
        } catch (ex: Exception) {
            throw ex
        }
    }


    override suspend fun SaveNewsToDB(newsList: List<ArticlesItem>) {
        try {
            newsDao.insertNewsToDB(newsList)
        } catch (ex: Exception) {
            throw ex
        }


    }
}