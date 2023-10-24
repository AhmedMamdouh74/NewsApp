package com.example.jetbackcompose.widgets.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domin.entities.ArticlesItemDTO
import com.example.domin.entities.SourcesItemDTO
import com.example.domin.repos.NewsRepository
import com.example.domin.repos.SourcesRepository
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
        mutableStateOf<List<SourcesItemDTO>>((listOf()))

    val newsList =
        mutableStateOf<List<ArticlesItemDTO>?>(listOf())
    var selectedIndex =
        mutableIntStateOf(0)


    fun getNewsBySource(sources: SourcesItemDTO, newsResponseState: MutableState<List<ArticlesItemDTO>?>) {

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

    }

    fun getNewsSources(category: String?, sourcesList: MutableState<List<SourcesItemDTO>>) {

        viewModelScope.launch(Dispatchers.IO) {


            try {
                var sourcesResponse = sourcesRepository?.getSourcesData(category ?: "")
                withContext(Dispatchers.Main) {
                    sourcesList.value = sourcesResponse ?: listOf()
                }

            } catch (ex: Exception) {
                Log.e("TAG", "${ex.message}")
            }



        }
    }
}
