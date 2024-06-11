package com.example.ezliv_mobile.ui.presentation.reservas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ezliv_mobile.ui.presentation.home.Cabecalho
import com.example.ezliv_mobile.ui.presentation.home.Rodape
import com.example.ezliv_mobile.ui.presentation.home.results.GetUserResult
import com.example.ezliv_mobile.ui.presentation.reservas.components.BoxReserva
import com.example.ezliv_mobile.ui.presentation.reservas.components.Reserva
import com.example.ezliv_mobile.ui.presentation.reservas.results.ReservaResult
import com.example.ezliv_mobile.ui.presentation.reservas.view_model.ReservaViewModel
import com.example.ezliv_mobile.ui.presentation.ui.components.Header
import com.example.ezliv_mobile.ui.presentation.ui.theme.EzlivmobileTheme


class Reserva : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EzlivmobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Reserva()
                }
            }
        }
    }
}

@Composable
fun Reservas(navController: NavController, reservaViewModel: ReservaViewModel){
    val result by reservaViewModel.postEventResult.observeAsState()

    val reservasResult by reservaViewModel.reservasResult.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Header(" Reservas", "Nova Reserva", "Minhas Reservas")
        Reserva()
        Rodape()
    }
}

