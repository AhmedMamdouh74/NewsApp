package com.example.jetbackcompose.widgets.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetbackcompose.api.model.ArticlesItem
import com.example.jetbackcompose.repo.NetworkHandler
import com.example.jetbackcompose.repo.news.NewsRepository
import com.example.jetbackcompose.repo.sources.SourcesRepository
import com.example.newsapp.model.Sources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val sourcesRepository: SourcesRepository,
    val newsRepository: NewsRepository,
) : ViewModel() {

    val sourcesList =
        mutableStateOf<List<Sources>>((listOf()))

    val newsList =
        mutableStateOf<List<ArticlesItem>?>(listOf())
    var selectedIndex =
        mutableIntStateOf(0)


    fun getNewsBySource(sources: Sources, newsResponseState: MutableState<List<ArticlesItem>?>) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newsResponse =
                    newsRepository.getNewsData(sources.id)
                withContext(Dispatchers.Main) {
                    newsResponseState.value = newsResponse
                }

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

        viewModelScope.launch(Dispatchers.IO) {


            try {
                var sourcesResponse = sourcesRepository?.getSourcesData(category ?: "")
                withContext(Dispatchers.Main) {
                    sourcesList.value = sourcesResponse ?: listOf()
                }

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
