package com.example.ezliv_mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Aviso() {
    Box (
        modifier = Modifier
            .width(328.dp)
            .height(208.dp)
            .background(
                color = Color(android.graphics.Color.parseColor("#D9D9D9"))
            ),
    ) {
        Column(

        ) {
            Text(
                text = "Manutenção no elevador",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(android.graphics.Color.parseColor("#D4D4D4")))
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center

            )
            Text(
                text = "Fechado durante o dia para a manutenção do elevador do bloco 5, " +
                        "nesse período é importante que façam o uso das escadas de emergência\n",
                modifier = Modifier
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp)


            )
            Text(
                text = "Lucas Pedrosa",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(android.graphics.Color.parseColor("#D4D4D4")))
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center

            )

        }
    }

}