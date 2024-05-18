package com.example.ezliv_mobile.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.ezliv_mobile.ui.presentation.home.Rodape
import com.example.ezliv_mobile.ui.presentation.reservas.components.BoxReserva
import com.example.ezliv_mobile.ui.presentation.ui.components.Header
import com.example.ezliv_mobile.ui.presentation.ui.theme.EzlivmobileTheme

class MinhaReserva : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EzlivmobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BoxReserva()
                }
            }
        }
    }
}

@Composable
fun MinhasReservas(

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Header("Reserva", "Nova Reserva", "Minhas Reservas")
        BoxReserva()
        Rodape()
    }

}

@Preview
@Composable
fun MihasReservasPreview() {
    EzlivmobileTheme {
        MinhasReservas()
    }
}