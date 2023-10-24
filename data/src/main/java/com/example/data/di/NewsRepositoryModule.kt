package com.example.data.di

import com.example.data.NetworkHandler
import com.example.data.api.WebServices
import com.example.data.database.NewsDao
import com.example.domin.repos.NewsOfflineDataSource
import com.example.domin.repos.NewsOnlineDataSource
import com.example.domin.repos.NewsRepository
import com.example.data.news.NewsOfflineDataSourceImpl
import com.example.data.news.NewsOnlineDataSourceImpl
import com.example.data.news.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsRepositoryModule {
    @Singleton
    @Provides
    fun provideNewsRepository(
        offlineDataSource: NewsOfflineDataSource,
        onlineDataSource: NewsOnlineDataSource,
        networkHandler: NetworkHandler
    ): NewsRepository {
        return NewsRepositoryImpl(offlineDataSource, onlineDataSource, networkHandler)

    }
    @Singleton
    @Provides
    fun provideOnlineDataSource(webServices: WebServices): NewsOnlineDataSource {
        return NewsOnlineDataSourceImpl(webServices)
    }
    @Singleton
    @Provides
    fun provideOfflineDataSource(newsDao: NewsDao): NewsOfflineDataSource {
        return NewsOfflineDataSourceImpl(newsDao)
    }


    }
