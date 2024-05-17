package com.example.ezliv_mobile.ui.presentation.entregas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezliv_mobile.ui.presentation.ui.components.AppBarPreview
import com.example.ezliv_mobile.ui.presentation.entregas.components.EntregaItem

@Composable
fun Entregas() {
    Scaffold (bottomBar = { AppBarPreview() }) { padding ->
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color(0xFF012A4A))
                        .padding(top = 14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .padding(start = 14.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )

                        Text(
                            text = "Entregas",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,

                            )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Para retirar",
                            color = Color.White,
                            fontSize = 16.sp,
                        )
                        Text(
                            text = "Entregue",
                            color = Color.White,
                            fontSize = 16.sp,
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    EntregaItem()
                    Spacer(modifier = Modifier.height(8.dp))
                    EntregaItem()
                    Spacer(modifier = Modifier.height(8.dp))
                    EntregaItem()
                    Spacer(modifier = Modifier.height(8.dp))

                }


            }

        }
    }

}

@Preview
@Composable
fun EntregasPreview() {
    Entregas()
}