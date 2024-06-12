package com.example.ezliv_mobile.ui.presentation.reservas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ezliv_mobile.ui.presentation.home.Rodape
import com.example.ezliv_mobile.ui.presentation.reservas.components.BoxReserva
import com.example.ezliv_mobile.ui.presentation.reservas.results.GetReservaResult
import com.example.ezliv_mobile.ui.presentation.reservas.results.ReservaResult
import com.example.ezliv_mobile.ui.presentation.reservas.view_model.ReservaViewModel
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
                }
            }
        }
    }
}

@Composable
fun MinhasReservas(navController: NavController, novaReservaViewModel: ReservaViewModel){

    val reservasResult by novaReservaViewModel.postEventResult.observeAsState()

    Scaffold(topBar = {
        Header(titulo = "Resevas", text1 = "Nova reserva", text2 = "Minhas reservas")
    },
        bottomBar = {
            Rodape()

        }) { padding ->
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
                            text = "Reservas",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,

                            )
                    }
                }
                when (reservasResult) {
                    is ReservaResult.Loading -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Carregando...",
                                    style = TextStyle(color = Color.Black),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    is ReservaResult.Error -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Erro ao carregar as reservas",
                                    style = TextStyle(color = Color.Black),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                    else -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            BoxReserva()
                        }

                    }
                }
            }
        }
    }
}