package com.ashutosh.pagingapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashutosh.pagingapi.Screen.PagingPage
import com.ashutosh.pagingapi.Screen.SplashScreen
import com.ashutosh.pagingapi.ui.theme.PagingApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingApiTheme {
                // A surface container using the 'background' color from the theme


                    Surface(
                        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()

                        NavHost(navController = navController , startDestination = "splash") {

                            composable("splash") {
                                SplashScreen(navController, this@MainActivity)
                            }

                            composable("main") {
                                PagingPage(navController,this@MainActivity)
                            }

                        }
                    }

            }
        }
    }
}

