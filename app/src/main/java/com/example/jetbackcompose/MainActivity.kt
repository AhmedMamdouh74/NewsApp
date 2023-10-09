package com.example.jetbackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

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

import com.example.jetbackcompose.ui.theme.JetbackComposeTheme
import com.example.jetbackcompose.api.ApiManager
import com.example.jetbackcompose.api.model.Category
import com.example.jetbackcompose.widgets.CategoriesContent
import com.example.jetbackcompose.widgets.DrawerBody
import com.example.jetbackcompose.widgets.DrawerHeader
import com.example.jetbackcompose.widgets.NewsFragment
import com.example.newsapp.model.Sources
import com.example.newsapp.model.SourcesResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {

    

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetbackComposeTheme {
                // A surface container using the 'background' color from the theme
                val sourcesList: MutableState<List<Sources>> = remember { mutableStateOf(listOf()) }




                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                ModalNavigationDrawer(
                    drawerContent = {
                        Column(modifier = Modifier.fillMaxSize()) {
                            DrawerHeader()
                            DrawerBody()


                        }
                    },
                    drawerState = drawerState
                ) {
                    Scaffold(topBar = { NewsAppBar(drawerState) }) {

                        //SourcesTaps(sourcesList = sourcesList.value)
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Constants.CATEORIES_ROUTE,
                            modifier = Modifier.padding(top = it.calculateTopPadding())
                        ) {
                            composable(route = Constants.CATEORIES_ROUTE) {
                                CategoriesContent(navController)
                            }
                            composable(
                                route = "${Constants.NEWS_ROUTE}/{category} ",
                                arguments = listOf(navArgument("category") {
                                    type = NavType.StringType

                                })
                            ) {
                                val argument=it.arguments?.getString("category")
                                NewsFragment(argument)

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

@Composable
fun SourcesTaps(sourcesList: List<Sources>) {
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetbackComposeTheme {
        Scaffold(topBar = { NewsAppBar(drawerState = DrawerState(DrawerValue.Closed)) }) {
            SourcesTaps(
                sourcesList = listOf(
                    Sources(name = "AbC"),
                    Sources(name = "abc"),
                    Sources(name = "abc"),
                    Sources(name = "abc")
                )
            )

        }

    }
}