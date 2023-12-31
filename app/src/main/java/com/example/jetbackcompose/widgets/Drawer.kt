package com.example.jetbackcompose.widgets

import android.view.View.OnClickListener
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetbackcompose.Constants
import com.example.jetbackcompose.R

@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.newsapp),
        modifier
            .fillMaxWidth(0.6f)
            .background(Color(0xff39A552), shape = RoundedCornerShape(0.dp))
            .padding(vertical = 16.dp),
        style = TextStyle(Color.White, fontSize = 20.sp),
        textAlign = TextAlign.Center
    )

}

@Composable
fun DrawerBody(navController: NavHostController, onCLoseDrawer: () -> Unit) {
    //  val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.6f)
            .background(color = Color.White)
            .padding(top = 8.dp)


    )
    {
        NewsDrawerItem(
            iconId = R.drawable.ic_list,
            textId = R.string.categories,
        ) {
            navController.navigate(Constants.CATEORIES_ROUTE)
            onCLoseDrawer()
        }
        NewsDrawerItem(
            iconId = R.drawable.ic_settings,
            textId = R.string.settings,
        ) {}
    }
}

@Composable
fun NewsDrawerItem(
    iconId: Int,
    textId: Int,
    onNewsDrawerItemClick: () -> Unit,
    ) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(7.dp)
            .clickable { onNewsDrawerItemClick() }
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "",
            Modifier.padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(id = textId),
            style = TextStyle(
                fontSize = 18.sp,
                color = Color(0xff303030),
                fontWeight = FontWeight.Bold
            )
        )

    }


}