package com.example.ezliv_mobile.ui.presentation.entregas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ezliv_mobile.ui.domain.entregas.models.PackageModel
import com.example.ezliv_mobile.ui.presentation.entregas.components.EntregaItem
import com.example.ezliv_mobile.ui.presentation.entregas.results.PackagesResult
import com.example.ezliv_mobile.ui.presentation.entregas.view_model.EntregasViewModel
import com.example.ezliv_mobile.ui.presentation.ui.components.AppBar
import kotlinx.coroutines.launch

@Composable
fun Entregas(
    navController: NavController,
    entregasViewModel: EntregasViewModel
) {
    val result by entregasViewModel.result.observeAsState()
    val entregasResult by entregasViewModel.entregasResult.observeAsState()
    val isDelivered = remember { mutableStateOf(false) }
    val selectedTab = remember { mutableStateOf(0) }
    val tabs = listOf("Para Retirar", "Entregue")
    val coroutineScope = rememberCoroutineScope()

    Scaffold(topBar = {
    Header(titulo = "Entregas", text1 = "Para Retirar", text2 = "Entregue")
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
                            text = "Entregas",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,

                            )
                    }

                    TabRow(
                        selectedTabIndex = selectedTab.value,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        tabs.forEachIndexed{index, title ->
                            Tab(selected = selectedTab.value == index,
                                onClick = { coroutineScope.launch { selectedTab.value = index } }) {
                                Text(
                                    text = title,
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }

                when (entregasResult) {
                    is PackagesResult.Loading -> {
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

                    is PackagesResult.Error -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Erro ao carregar as entregas",
                                    style = TextStyle(color = Color.Black),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }


                    is PackagesResult.Success -> {
                        val entregasRecebidas = (entregasResult as PackagesResult.Success)
                            .data.filter { entrega -> entrega.delivered }
                        val entregasNaoRecebidas = (entregasResult as PackagesResult.Success)
                            .data.filter { entrega -> !entrega.delivered }
                        LazyColumn() {
                            if (selectedTab.value == 0) {
                                items(entregasNaoRecebidas) { entrega ->
                                    EntregaItem(
                                        model = PackageModel(
                                            entrega.id,
                                            entrega.code,
                                            entrega.description,
                                            entrega.condominiumId,
                                            entrega.apartmentId,
                                            entrega.delivered,
                                            entrega.receiptDate,
                                            entrega.deliveryDate,
                                            entrega.apartmentName,
                                            entrega.towerName
                                        )
                                    )
                                }
                            } else {
                                items(entregasRecebidas) { entrega ->
                                    EntregaItem(
                                        model = PackageModel(
                                            entrega.id,
                                            entrega.code,
                                            entrega.description,
                                            entrega.condominiumId,
                                            entrega.apartmentId,
                                            entrega.delivered,
                                            entrega.receiptDate,
                                            entrega.deliveryDate,
                                            entrega.apartmentName,
                                            entrega.towerName
                                        )
                                    )
                                }
                            }

                        }
                    }
                }


            }


        }


    }
}
@Composable
fun Header (titulo: String, text1: String, text2: String){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .height(106.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF012A4A))
                .height(58.dp)
                .padding(10.dp),
            Arrangement.Absolute.Left,
            Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                tint = Color.White

            )
            Text(text = titulo,
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp,
                color = Color.White)
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF012A4A))
                .height(48.dp),
            Arrangement.SpaceAround,
            Alignment.CenterVertically

        ){
            Text(text = text1,
                color = Color.White
            )
            Text(text = text2,
                color = Color.White)
        }
    }
}

@Composable
fun Rodape() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF012A4A))
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemRodape()
        itemRodape1()
        itemRodape2()
        itemRodape3()
    }
}

@Composable
fun itemRodape() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "home",
            tint = Color.White
        )
        Text(
            text = "Início",
            style = TextStyle(color = Color.White)
        )
    }
}

@Composable
fun itemRodape1() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "email",
            tint = Color.White
        )
        Text(
            text = "Entregas",
            style = TextStyle(color = Color.White)
        )
    }
}

@Composable
fun itemRodape2() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "apartamento",
            tint = Color.White
        )
        Text(
            text = "Apartamento",
            style = TextStyle(color = Color.White)
        )
    }
}

@Composable
fun itemRodape3() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "Calendario",
            tint = Color.White
        )
        Text(
            text = "Reservas",
            style = TextStyle(color = Color.White)
        )
    }
}




