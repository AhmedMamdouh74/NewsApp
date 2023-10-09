package com.example.jetbackcompose.widgets

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.jetbackcompose.Constants
import com.example.jetbackcompose.SourcesTaps
import com.example.jetbackcompose.api.ApiManager
import com.example.jetbackcompose.api.model.Category
import com.example.newsapp.model.Sources
import com.example.newsapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewsFragment(category: String?) {
    val sourcesList = remember {
        mutableStateOf<List<Sources>>((listOf()))
    }

    getNewsSources(category,sourcesList )
    Column {
        SourcesTaps(sourcesList.value)
    }

}

fun getNewsSources(category: String?, sourcesList: MutableState<List<Sources>>) {
    ApiManager
        .getApis()
        .getSources(Constants.API_KEY,category= category?: "")
        .enqueue(object : Callback<SourcesResponse> {
            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>
            ) {
                val body = response.body()
                Log.e("TAG", "onResponse: ${body?.status}")
                Log.e("TAG", "onResponse: ${body?.sources}")
                sourcesList.value = body?.sources ?: listOf()
            }

            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {


            }
        })
}