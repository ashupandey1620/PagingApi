package com.ashutosh.pagingapi.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.ashutosh.pagingapi.MainActivity
import com.ashutosh.pagingapi.R
import com.ashutosh.pagingapi.ViewModel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PagingPage(mainActivity: MainActivity) {





    val mainViewModel : MainViewModel = hiltViewModel()
    val parentItems = mainViewModel.getPosts.observeAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getPostsFromNetwork()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){


        Scaffold(modifier = Modifier.fillMaxSize() ,
            topBar = {

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

                    if (parentItems.value.isNullOrEmpty()) {

                        Column(
                            modifier = Modifier.fillMaxSize() ,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.nodata) ,
                                contentDescription = "nodata" ,
                                modifier = Modifier.size(200.dp)
                            )

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 30.dp)
                                    .fillMaxWidth() ,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp ,
                                lineHeight = 18.sp ,
                                text = "No Registered Events",
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

                            parentItems.value?.let {
                                items(count = it.size ,
                                    key = { parentItems.value!!.get(it).id }
                                ) { it ->
                                    ColumnItemRegisteredEvent(
                                        parentItems.value!!.get(it).id ,
                                        parentItems.value!!.get(it).title ,
                                        parentItems.value!!.get(it).body ,
                                        parentItems.value!!.get(it).userId ,
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
}



@Composable
fun ColumnItemRegisteredEvent(
    id: Int? ,
    title: String? ,
    body: String? ,
    userId: Int? ,
    rowItemIndex: Int?
    ) {


    val context = LocalContext.current

    Column(modifier = Modifier
        .padding(top = 5.dp)
        .height(150.dp)
        .fillMaxWidth()
        .background(Color(0xFFfdc044))
        ,

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
                        text = id.toString() ,
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
                        text = title.toString() ,
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
                        text = body.toString() ,
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
                        text = userId.toString() ,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )



                }
            }
        }


    }
}