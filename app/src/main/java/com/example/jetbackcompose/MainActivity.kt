package com.example.jetbackcompose

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetbackcompose.newsdetails.NewsDetails

import com.example.jetbackcompose.ui.theme.JetbackComposeTheme
import com.example.jetbackcompose.widgets.categories.CategoriesContent
import com.example.jetbackcompose.widgets.DrawerBody
import com.example.jetbackcompose.widgets.DrawerHeader
import com.example.jetbackcompose.newsdetails.NewsDetails
import com.example.jetbackcompose.widgets.news.NewsFragment

import com.example.newsapp.model.Sources
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetbackComposeTheme {
                // A surface container using the 'background' color from the theme
                val sourcesList: MutableState<List<Sources>> = remember { mutableStateOf(listOf()) }
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()


                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                ModalNavigationDrawer(
                    drawerContent = {
                        Column(modifier = Modifier.fillMaxSize()) {
                            DrawerHeader()
                            DrawerBody(navController) {
                                scope.launch {
                                    drawerState.close()
                                }

                            }


                        }
                    },
                    drawerState = drawerState
                ) {
                    Scaffold(topBar = { NewsAppBar(drawerState) }) {

                        //SourcesTaps(sourcesList = sourcesList.value)

                        NavHost(
                            navController = navController,
                            startDestination = Constants.CATEORIES_ROUTE,
                            modifier = Modifier.padding(top = it.calculateTopPadding())
                        ) {
                            composable(route = Constants.CATEORIES_ROUTE) {
                                CategoriesContent(navController)
                            }
                            composable(
                                route = "${Constants.NEWS_ROUTE}/{category}",
                                arguments = listOf(navArgument("category") {
                                    type = NavType.StringType

                                })
                            ) {
                                val argument = it.arguments?.getString("category")
                                NewsFragment(argument, navController)

                            }
                            composable(
                                route = Constants.DETAILS
                            ) {
                                NewsDetails(
                                    navController = navController
                                )
                            }

                        }
                    }
                }


            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.news),
                style = TextStyle(
                    color = Color.White, fontSize = 22.sp
                )
            )
        },
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 26.dp,
                bottomEnd = 26.dp
            )
        ),
        colors = TopAppBarDefaults
            .centerAlignedTopAppBarColors(
                containerColor = Color(0xff39A552),
                navigationIconContentColor = Color.White
            ),
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }


            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu"
                )
            }
        }

    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetbackComposeTheme {
        Scaffold(topBar = { NewsAppBar(drawerState = DrawerState(DrawerValue.Closed)) }) {

        }

    }
}