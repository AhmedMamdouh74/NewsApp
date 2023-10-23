package com.example.jetbackcompose.di

import com.example.jetbackcompose.api.WebServices
import com.example.jetbackcompose.database.SourcesDao
import com.example.jetbackcompose.repo.NetworkHandler
import com.example.jetbackcompose.repo.sources.SourcesOfflineDataSource
import com.example.jetbackcompose.repo.sources.SourcesOfflineDataSourceImpl
import com.example.jetbackcompose.repo.sources.SourcesOnlineDataSource
import com.example.jetbackcompose.repo.sources.SourcesOnlineDataSourceImpl
import com.example.jetbackcompose.repo.sources.SourcesRepository
import com.example.jetbackcompose.repo.sources.SourcesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourcesRepositoryModule {
    @Singleton
    @Provides
    fun provideSourcesRepository(
        sourceOfflineDataSource: SourcesOfflineDataSource,
        sourceOnlineDataSource: SourcesOnlineDataSource,
        networkHandler: NetworkHandler
    ): SourcesRepository {
        return SourcesRepositoryImpl(
            sourceOnlineDataSource,
            sourceOfflineDataSource,
            networkHandler
        )

    }
    @Singleton
    @Provides
    fun provideSourcesOnlineDataSource(webServices: WebServices): SourcesOnlineDataSource {
        return SourcesOnlineDataSourceImpl(webServices)
    }
    @Singleton
    @Provides
    fun provideSourcesOfflineDataSource(sourcesDao: SourcesDao): SourcesOfflineDataSource {
        return SourcesOfflineDataSourceImpl(sourcesDao)
    }
    @Singleton
    @Provides
    fun provideNetworkHandler(): NetworkHandler {
        return object : NetworkHandler {
            override fun isOnline(): Boolean {
                return true
            }

        }
    }

}