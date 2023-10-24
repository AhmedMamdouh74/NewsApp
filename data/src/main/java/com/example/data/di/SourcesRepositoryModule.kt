package com.example.jetbackcompose.di

import com.example.data.NetworkHandler
import com.example.data.api.WebServices
import com.example.data.database.SourcesDao
import com.example.domin.repos.SourcesOfflineDataSource
import com.example.domin.repos.SourcesOnlineDataSource
import com.example.domin.repos.SourcesRepository
import com.example.data.sources.SourcesOfflineDataSourceImpl
import com.example.data.sources.SourcesOnlineDataSourceImpl
import com.example.data.sources.SourcesRepositoryImpl
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