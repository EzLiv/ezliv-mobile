package com.example.ezliv_mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Header (){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(106.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Red)
                .height(58.dp)
                .padding(10.dp)
        ){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back"

            )
            Text(text = "Apartamento")
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Green)
                .height(48.dp),
            Arrangement.SpaceAround,
            Alignment.CenterVertically

        ){
            Text(text = "Nova Reserva")
            Text(text = "Minhas Reservas")
        }
    }
}

@Preview
@Composable
fun HeaderPreview() {
    Header()
}