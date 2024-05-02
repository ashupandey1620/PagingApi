package com.ashutosh.pagingapi.Screen.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ashutosh.pagingapi.R


@Composable
fun Toolbar(navController: NavController) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Top)
                .background(Color.LightGray)
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp)
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically
        )
        {

            Image(
                painter = painterResource(id = R.drawable.paging) , contentDescription = "" ,
                modifier = Modifier.height(35.dp)
            )

            Column(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .wrapContentHeight()
                    .wrapContentWidth() ,
            ) {

//                Text(
//                    modifier = Modifier
//                        .wrapContentSize() ,
//                    textAlign = TextAlign.Center ,
//                    fontSize = 14.sp ,
//                    lineHeight = 15.sp ,
//                    text = "Assignment By:" ,
//                    fontWeight = FontWeight.Bold
//                )
//
//                Text(
//                    modifier = Modifier
//                        .wrapContentSize() ,
//                    textAlign = TextAlign.Center ,
//                    fontSize = 12.sp ,
//                    lineHeight = 13.sp ,
//                    text = "Ashutosh Pandey" ,
//                    fontWeight = FontWeight.Bold
//                )

            }


        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.DarkGray))
    }
}
