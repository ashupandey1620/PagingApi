package com.ashutosh.pagingapi.Screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ashutosh.pagingapi.MainActivity
import com.ashutosh.pagingapi.R
import com.ashutosh.pagingapi.Screen.Composable.Toolbar
import com.ashutosh.pagingapi.ViewModel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PagingPage(navController: NavHostController , mainActivity: MainActivity) {



    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(composition = composition, restartOnPlay = true,
        iterations = LottieConstants.IterateForever)



    val mainViewModel : MainViewModel = hiltViewModel()
//    val parentItems = mainViewModel.getPosts.observeAsState()



    val parentItems = mainViewModel.getAllPosts.collectAsLazyPagingItems()



    LaunchedEffect(Unit) {
        mainViewModel.getPostsFromNetwork()
    }



        Scaffold(modifier = Modifier.fillMaxSize() ,
            topBar = {

                     Toolbar(navController = navController)
            } ,
            bottomBar = {

            },
            content = {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)

                ) {


                    val scope = rememberCoroutineScope()

                    if (parentItems.itemCount==0) {

                        Column(
                            modifier = Modifier.fillMaxSize() ,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            LottieAnimation(
                                modifier = Modifier.size(80.dp) ,
                                composition = composition ,
                                progress = { progress })

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 30.dp)
                                    .fillMaxWidth() ,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp ,
                                lineHeight = 18.sp ,
                                text = "Loading.....",
                                color = Color(0xFFfdc044) ,
                                fontWeight = FontWeight.Medium,

                                )
                        }

                    } else {

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(65.dp)
                        )

                        LazyColumn(
                            contentPadding = PaddingValues() ,
                            userScrollEnabled = true ,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {

                            parentItems?.let {
                                items(count = parentItems.itemCount ,
                                ) { it ->
                                    ColumnItemRegisteredEvent(
                                        parentItems.get(it)!!.id ,
                                        parentItems.get(it)!!.title ,
                                        parentItems.get(it)!!.body ,
                                        parentItems.get(it)!!.userId ,
                                        it ,
                                    )
                                }
                            }
                        }
                    }

                }

            }
        )
}



@Composable
fun ColumnItemRegisteredEvent(
    id: String ,
    title: String? ,
    body: String? ,
    userId: Int? ,
    rowItemIndex: Int?
    ) {

    var show by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(modifier = Modifier
        .padding(top = 5.dp)
        .wrapContentHeight()
        .fillMaxWidth()
        .background(Color(0xFFfdc044))
        .clickable {
            show = !show
        },
        ){


        Row(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 9.dp)
            .padding(vertical = 7.dp)) {



            Column {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight() ,
                    horizontalAlignment = Alignment.CenterHorizontally ,
                    verticalArrangement = Arrangement.Center

                ) {

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth() ,
                        textAlign = TextAlign.Start ,
                        fontSize = 18.sp ,
                        lineHeight = 18.sp ,
                        text = "ID : $id" ,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )


                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                    )

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth() ,
                        textAlign = TextAlign.Start ,
                        fontSize = 18.sp ,
                        lineHeight = 18.sp ,
                        text = "Title :" ,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth() ,
                        textAlign = TextAlign.Start ,
                        fontSize = 15.sp ,
                        lineHeight = 15.sp ,
                        text = "$title" ,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )



                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                    )


                    AnimatedVisibility(
                        show ,
                        enter = expandVertically() ,
                        exit = shrinkVertically()
                    ) {

                        Column {

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth() ,
                                textAlign = TextAlign.Start ,
                                fontSize = 18.sp ,
                                lineHeight = 18.sp ,
                                text = "Body :" ,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth() ,
                                textAlign = TextAlign.Start ,
                                fontSize = 15.sp ,
                                lineHeight = 15.sp ,
                                text = "$body" ,
                                color = Color.DarkGray,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp)
                            )

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth() ,
                                textAlign = TextAlign.Start ,
                                fontSize = 18.sp ,
                                lineHeight = 18.sp ,
                                text = "UserID :" ,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth() ,
                                textAlign = TextAlign.Start ,
                                fontSize = 15.sp ,
                                lineHeight = 15.sp ,
                                text = "$userId" ,
                                color = Color.DarkGray,
                                fontWeight = FontWeight.Bold
                            )



                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp)
                            )

                        }


                    }

                }
            }
        }


    }
}