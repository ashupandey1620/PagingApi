package com.ashutosh.pagingapi.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.ashutosh.pagingapi.MainActivity
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController , mainActivity: MainActivity) {

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("main")
    }

    Column(modifier = Modifier.fillMaxSize()
        .background(Color.Green)){

    }
}