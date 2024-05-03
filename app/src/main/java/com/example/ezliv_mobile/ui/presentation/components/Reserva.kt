package com.example.ezliv_mobile.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Reserva(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(184.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(color = Color(0xFFD4D4D4)),

            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Text(text = "Churrasqueira")
        }
        Column(modifier = Modifier
            .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Dia: 27/08/2024")
            Text(text = "Horário: 19:30")
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF012A4A))
                    .height(30.dp)
                    .width(105.dp)
                    .clip(RoundedCornerShape(8.dp))
            ){

            }
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF012A4A))
                    .height(30.dp)
                    .width(105.dp)
                    .clip(RoundedCornerShape(8.dp))
            ){

            }
        }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReservaPreview() {
    Reserva()
}