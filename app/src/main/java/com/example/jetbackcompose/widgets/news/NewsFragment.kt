package com.example.jetbackcompose.widgets.news

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetbackcompose.Constants
import com.example.jetbackcompose.R
import com.example.jetbackcompose.api.model.ArticlesItem
import com.example.newsapp.model.Sources


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NewsFragment(category: String?, navController: NavController,viewModel: NewsViewModel= androidx.lifecycle.viewmodel.compose.viewModel()) {


    viewModel.getNewsSources(category,viewModel.sourcesList)
    Column {
        SourcesTaps(viewModel.sourcesList.value,viewModel. newsList)
        NewsList(articlesList =viewModel.newsList.value ?: listOf(), navController)
    }

}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NewsList(articlesList: List<ArticlesItem>, navController: NavController) {
    LazyColumn {
        items(articlesList.size) {
            NewsCard(articlesItem = articlesList.get(it), navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(articlesItem: ArticlesItem, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 12.dp),
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.apply {
                set(Constants.DETAILS, articlesItem)
            }
            navController.navigate(Constants.DETAILS)

        }

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
fun SourcesTaps(sourcesList: List<Sources>, newsResponseState: MutableState<List<ArticlesItem>?>,viewModel: NewsViewModel= androidx.lifecycle.viewmodel.compose.viewModel()) {

    if (sourcesList.isNotEmpty()) {
        ScrollableTabRow(
            selectedTabIndex = viewModel.selectedIndex.value,
            containerColor = Color.Transparent,
            divider = {},
            indicator = {}) {
            sourcesList.forEachIndexed { index, sources ->
                if (viewModel.selectedIndex.value == index) {
                    viewModel. getNewsBySource(sources, newsResponseState = newsResponseState)
                }
                Tab(
                    selected = viewModel.selectedIndex.value == index,
                    onClick = {
                        viewModel.selectedIndex.value = index

                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color(0xff39A552),
                    modifier = if (viewModel.selectedIndex.value == index) Modifier
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





@Preview(name = "News Card", showSystemUi = true)
@Composable
fun NewsCardPreview() {


}