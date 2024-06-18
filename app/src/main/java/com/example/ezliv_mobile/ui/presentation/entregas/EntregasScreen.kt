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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
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
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF012A4A),
            ),
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Entregas",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                    )
                }
            },
        )
    },
        bottomBar = {
            AppBar(navController)
        }) {
        Column(Modifier.padding(it)) {
            TabRow(
                selectedTabIndex = selectedTab.value,
                modifier = Modifier.fillMaxWidth(),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab.value]),
                        color = Color(0xFF012A4A)
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab.value == index,
                        onClick = { selectedTab.value = index },
                        text = { Text(text = title, fontWeight = FontWeight.W500) },
                        selectedContentColor = Color(0xFF012A4A),
                        unselectedContentColor = Color.Gray,
                    )
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
                    LazyColumn(Modifier.padding(16.dp)) {
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





