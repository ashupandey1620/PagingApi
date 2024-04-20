package com.ashutosh.pagingapi.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ashutosh.pagingapi.MainActivity
import com.ashutosh.pagingapi.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController , mainActivity: MainActivity) {

    LaunchedEffect(Unit) {
        delay(2000)
        navController.popBackStack()
        navController.navigate("main")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1E1E1E) ,
                        Color(0xFF1E1E1E)
                    ) ,
                    start = Offset(0.0979f , 0f) ,
                    end = Offset(0.2064f , 0f)
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.paging) ,
            contentDescription = null ,
            contentScale = ContentScale.Fit ,
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .height(150.dp)
                .fillMaxWidth()
            ,

            )


        Spacer(modifier = Modifier.height(20.dp))

    }
}