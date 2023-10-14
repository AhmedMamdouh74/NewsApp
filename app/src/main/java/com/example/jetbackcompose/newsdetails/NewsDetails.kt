package com.example.jetbackcompose.newsdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetbackcompose.Constants
import com.example.jetbackcompose.R
import com.example.jetbackcompose.api.model.ArticlesItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetails(navController: NavController) {
    val news =
        navController.previousBackStackEntry?.savedStateHandle?.get<ArticlesItem>(Constants.DETAILS)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 12.dp),


        ) {
        GlideImage(
            model = news?.urlToImage ?: "",
            contentDescription = "News Picture",
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(), contentScale = ContentScale.Crop
        )
        Text(
            text = news?.author ?: "",
            style = TextStyle(color = colorResource(id = R.color.grey))
        )

        Text(
            text = news?.title ?: "",
            style = TextStyle(colorResource(id = R.color.colorBlack))
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(

            text = news?.publishedAt ?: "",

            modifier = Modifier.align(Alignment.End),
            style = TextStyle(
                color = colorResource(id = R.color.grey2),
            )
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 12.dp)
        ) {
            Text(

                text = news?.content ?: "",

                style = TextStyle(
                    color = colorResource(id = R.color.grey2),
                )
            )

        }


    }

}