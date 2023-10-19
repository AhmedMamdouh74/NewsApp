package com.example.jetbackcompose.widgets.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetbackcompose.Constants
import com.example.jetbackcompose.api.ApiManager
import com.example.jetbackcompose.api.model.ArticlesItem
import com.example.jetbackcompose.database.NewsLocalDataBase
import com.example.jetbackcompose.repo.NetworkHandler
import com.example.jetbackcompose.repo.sources.SourcesOfflineDataSource
import com.example.jetbackcompose.repo.sources.SourcesOfflineDataSourceImpl
import com.example.jetbackcompose.repo.sources.SourcesOnlineDataSource
import com.example.jetbackcompose.repo.sources.SourcesOnlineDataSourceImpl
import com.example.jetbackcompose.repo.sources.SourcesRepository
import com.example.jetbackcompose.repo.sources.SourcesRepositoryImpl
import com.example.newsapp.model.Sources
import kotlinx.coroutines.launch


class NewsViewModel : ViewModel() {
    val sourcesList =
        mutableStateOf<List<Sources>>((listOf()))

    val newsList =
        mutableStateOf<List<ArticlesItem>?>(listOf())
    var selectedIndex =
        mutableIntStateOf(0)
    var sourcesRepository: SourcesRepository? = null
    var sourcesOnlineDataSource: SourcesOnlineDataSource? = null

    var sourcesOfflineDataSource: SourcesOfflineDataSource? = null

    var networkHandler: NetworkHandler? = null


    fun getNewsBySource(sources: Sources, newsResponseState: MutableState<List<ArticlesItem>?>) {

        viewModelScope.launch {
            try {
                val newsResponse =
                    ApiManager
                        .getApis()
                        .getNewsBySource(Constants.API_KEY, sources.id ?: "")
                newsResponseState.value = newsResponse?.articles
            } catch (ex: Exception) {
                Log.e("TAG", "${ex.message} ")

            }

        }

//            .enqueue(object : Callback<NewsResponse> {
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//                    val newsResponse = response.body()
//
//                    Log.e("TAG", "statusResponse: ${newsResponse?.status}")
//                    Log.e("TAG", "onNewsResponse: ${newsResponse?.articles}")
//
//                }
//
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//
//                }
//            })
    }

    fun getNewsSources(category: String?, sourcesList: MutableState<List<Sources>>) {

        viewModelScope.launch {
            networkHandler = object : NetworkHandler {
                override fun isOnline(): Boolean {
                    return true
                }
            }
            sourcesOnlineDataSource = SourcesOnlineDataSourceImpl(ApiManager.getApis())
            sourcesOfflineDataSource =
                SourcesOfflineDataSourceImpl(NewsLocalDataBase.getInstance().getSourcesDao())

            sourcesRepository = SourcesRepositoryImpl(
                sourcesOnlineDataSource!!,
                sourcesOfflineDataSource!!,
                networkHandler = networkHandler!!
            )
            try {
                var sourcesResponse = sourcesRepository?.getSourcesData(category ?: "")
                sourcesList.value = sourcesResponse ?: listOf()
            } catch (ex: Exception) {
                Log.e("TAG", "${ex.message}")
            }


//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    val body = response.body()
//                    Log.e("TAG", "onResponse: ${body?.status}")
//                    Log.e("TAG", "onResponse: ${body?.sources}")
//
//                }
//
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//
//
//                }
//            })
        }
    }
}
