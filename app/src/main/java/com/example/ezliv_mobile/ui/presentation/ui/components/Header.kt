package com.example.ezliv_mobile.ui.presentation.ui.components

import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezliv_mobile.ui.presentation.home.Mural

@Composable
fun Header (titulo: String, text1: String, text2: String
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(106.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF012A4A))
                .height(58.dp)
                .padding(10.dp),
            Arrangement.Absolute.Left,
            Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                tint = Color.White

            )
            Text(text = titulo,
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp,
                color = Color.White)
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF012A4A))
                .height(48.dp),
            Arrangement.SpaceAround,
            Alignment.CenterVertically

        ){
            Text(text = text1,
                color = Color.White,
            )
            Text(text = text2,
                color = Color.White)
        }
    }
}

@Preview
@Composable
fun HeaderPreview() {
    Header("exemplo", "exemplo", "exemplo" )
}