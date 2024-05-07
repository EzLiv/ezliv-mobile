package com.example.ezliv_mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Reserva() {
    Box(modifier = Modifier
        .background(color = Color(0xFFD9D9D9))
        .width(350.dp)
        .height(200.dp)
        .clip(shape = RoundedCornerShape(12.dp)))
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                )
         {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .background(color = Color(0xFFCDCBCB)),

                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = "Churrasqueira",

                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF012A4A),
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Dia: 27/08/2024",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF012A4A),

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp, 20.dp, 0.dp, 0.dp)

                )
                Text(
                    text = "Horário: 19:30",
                    fontSize = 16.sp,
                    color = Color(0xFF012A4A),

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp, 0.dp, 0.dp, 0.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .width(115.dp)
                            .height(36.dp)
                            .background(color = Color(0xFF012A4A)),

                        colors = ButtonDefaults.buttonColors(
                            Color.Transparent
                        ),
                        onClick = {  }) {
                        Text(
                            text = "Editar", modifier = Modifier,
                            color = Color.White,
                            fontSize = 15.sp,
                            )
                    }
                    Button(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .width(115.dp)
                            .height(36.dp)
                            .background(color = Color(0xFF012A4A)),

                        colors = ButtonDefaults.buttonColors(
                            Color.Transparent
                        ),
                        onClick = {  }) {
                        Text(
                            text = "Cancelar", modifier = Modifier,
                            color = Color.White,
                            fontSize = 16.sp,

                            )

                    }
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