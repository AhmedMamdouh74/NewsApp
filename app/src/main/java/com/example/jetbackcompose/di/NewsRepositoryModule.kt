package com.example.jetbackcompose.di

import com.example.jetbackcompose.api.WebServices
import com.example.jetbackcompose.database.NewsDao
import com.example.jetbackcompose.repo.NetworkHandler
import com.example.jetbackcompose.repo.news.NewsOfflineDataSource
import com.example.jetbackcompose.repo.news.NewsOfflineDataSourceImpl
import com.example.jetbackcompose.repo.news.NewsOnlineDataSource
import com.example.jetbackcompose.repo.news.NewsOnlineDataSourceImpl
import com.example.jetbackcompose.repo.news.NewsRepository
import com.example.jetbackcompose.repo.news.NewsRepositoryImpl
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
