package com.example.jetbackcompose.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetbackcompose.Constants
import com.example.jetbackcompose.R
import com.example.jetbackcompose.api.ApiManager
import com.example.jetbackcompose.api.model.ArticlesItem
import com.example.jetbackcompose.api.model.NewsResponse
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
    val newsList = remember {
        mutableStateOf<List<ArticlesItem>?>(listOf())
}

    getNewsSources(category, sourcesList)
    Column {
        SourcesTaps(sourcesList.value,newsList)
        NewsList(articlesList =newsList.value?: listOf() )
    }

}
@Composable
fun NewsList(articlesList: List<ArticlesItem>) {
    LazyColumn {
        items(articlesList.size) {
            NewsCard(articlesItem = articlesList.get(it))
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(articlesItem: ArticlesItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 12.dp),

        ) {
        GlideImage(
            model = articlesItem.urlToImage ?: "",
            contentDescription = "News Picture",
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(), contentScale = ContentScale.Crop
        )
        Text(
            text = articlesItem.author ?: "",
            style = TextStyle(color = colorResource(id = R.color.grey))
        )
        Text(
            text = articlesItem.title ?: "",
            style = TextStyle(colorResource(id = R.color.colorBlack))
        )
        Text(
            text = articlesItem.publishedAt ?: "",

            modifier = Modifier.align(Alignment.End),
            style = TextStyle(
                color = colorResource(id = R.color.grey2),
            )
        )
    }
}
@Composable
fun SourcesTaps(sourcesList: List<Sources>,newsResponseState: MutableState<List<ArticlesItem>?>)  {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    if (sourcesList.isNotEmpty()) {
        ScrollableTabRow(
            selectedTabIndex = selectedIndex,
            containerColor = Color.Transparent,
            divider = {},
            indicator = {}) {
            sourcesList.forEachIndexed { index, sources ->
                if (selectedIndex==index) {
                    getNewsBySource(sources,newsResponseState=newsResponseState)
                }
                Tab(
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index

                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color(0xff39A552),
                    modifier = if (selectedIndex == index) Modifier
                        .padding(end = 2.dp)
                        .background(
                            Color(0xff39A552),
                            RoundedCornerShape(50)
                        )
                    else
                        Modifier
                            .padding(end = 2.dp)
                            .border(2.dp, Color(0xff39A552), RoundedCornerShape(50)),
                    text = { Text(text = sources.name ?: "") }


                )
            }
        }
    }
}


fun getNewsBySource(sources: Sources, newsResponseState: MutableState<List<ArticlesItem>?>) {
    ApiManager
        .getApis()
        .getNewsBySource(Constants.API_KEY, sources.id ?: "")
        .enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                val newsResponse=response.body()
                newsResponseState.value=newsResponse?.articles
                Log.e("TAG", "statusResponse: ${newsResponse?.status}")
                Log.e("TAG", "onNewsResponse: ${newsResponse?.articles}")

            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }
        })
}

fun getNewsSources(category: String?, sourcesList: MutableState<List<Sources>>) {
    ApiManager
        .getApis()
        .getSources(Constants.API_KEY, category = category ?: "")
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
@Preview(name = "News Card", showSystemUi = true)
@Composable
fun NewsCardPreview() {
    NewsCard(
        articlesItem = ArticlesItem(
            "10 / 9 / 2023",
            "BBC News",
            "URL To Image",
            LoremIpsum(15).toString(), title = "Title ", content = LoremIpsum(20).toString()

        )
    )
}