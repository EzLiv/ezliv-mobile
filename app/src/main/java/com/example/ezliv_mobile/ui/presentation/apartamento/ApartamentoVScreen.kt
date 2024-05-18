package com.example.ezliv_mobile.ui.screens


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezliv_mobile.ui.presentation.apartamento.components.Visitante
import com.example.ezliv_mobile.ui.presentation.home.Rodape
import com.example.ezliv_mobile.ui.presentation.reservas.components.BoxReserva
import com.example.ezliv_mobile.ui.presentation.ui.components.Header
import com.example.ezliv_mobile.ui.presentation.ui.theme.EzlivmobileTheme


class ApartamentoV : ComponentActivity(){
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
fun ApartamentoVScreen(

){
    Scaffold (topBar = {
        Header("Apartamento", "Moradores", "Visitantes")
    }, bottomBar = {
        Rodape()
    }){
        Box(modifier = Modifier.padding(it)){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(30.dp, 30.dp)
            ) {
                Visitante()
            }
        }

    }


}

@Preview
@Composable
fun ApartamentoVScreenPreview() {
    EzlivmobileTheme {
        ApartamentoVScreen()
    }
}