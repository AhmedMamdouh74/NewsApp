package com.example.jetbackcompose.widgets.news
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetbackcompose.Constants
import com.example.jetbackcompose.api.ApiManager
import com.example.jetbackcompose.api.model.ArticlesItem
import com.example.newsapp.model.Sources
import kotlinx.coroutines.launch


class NewsViewModel : ViewModel() {
    val sourcesList =
        mutableStateOf<List<Sources>>((listOf()))

    val newsList =
        mutableStateOf<List<ArticlesItem>?>(listOf())
    var selectedIndex =
        mutableIntStateOf(0)


    fun getNewsBySource(sources: Sources, newsResponseState: MutableState<List<ArticlesItem>?>) {
        viewModelScope.launch {
            try {
                val newsResponse =
                    ApiManager
                        .getApis()
                        .getNewsBySource(Constants.API_KEY, sources.id ?: "")
                newsResponseState.value = newsResponse?.articles
            } catch (ex: Exception) {

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
            try {
                val sourcesResponse = ApiManager
                    .getApis()
                    .getSources(Constants.API_KEY, category = category ?: "")
                sourcesList.value = sourcesResponse.sources ?: listOf()
            } catch (ex: Exception) {

            }
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
