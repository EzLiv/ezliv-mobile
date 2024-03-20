package com.example.ezliv_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezliv_mobile.ui.theme.EzlivmobileTheme

class Mural : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EzlivmobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Aviso()
                }
            }
        }
    }
}

@Composable
fun Aviso() {
    Box (
        modifier = Modifier
            .width(328.dp)
            .height(208.dp)

    ) {
        Column(

        ) {
            Text(
                text = "Manutenção no elevador",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
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
                    .background(Color.Gray)
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center

            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    EzlivmobileTheme {
        Aviso()
    }
}