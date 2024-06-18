package com.example.ezliv_mobile.ui.presentation.apartamento

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ezliv_mobile.ui.domain.apartment.models.Resident
import com.example.ezliv_mobile.ui.presentation.apartamento.components.VisitorCard
import com.example.ezliv_mobile.ui.presentation.apartamento.results.GetVisitorsResult
import com.example.ezliv_mobile.ui.presentation.apartamento.results.ResidentResult
import com.example.ezliv_mobile.ui.presentation.apartamento.view_model.ApartmentViewModel
import com.example.ezliv_mobile.ui.presentation.ui.components.AppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApartmentScreen(navController: NavController, apartmentViewModel: ApartmentViewModel) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabTitles = listOf("Moradores", "Visitantes")

    val selectedTabColor = Color(0xFF012A4A)

    val residentResult by apartmentViewModel.residentResult.observeAsState()

    val visitorsResult by apartmentViewModel.getVisitorsResult.observeAsState()


    Scaffold(
        topBar = {
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
                            text = "Meu Apartamento",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W500,
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            if (selectedTabIndex == 1) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("addVisitor")
                    },
                    containerColor = Color(0xFF012A4A),
                    contentColor = Color.White
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Visitor")
                }
            }
        },
        bottomBar = {
            AppBar(navController)
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth(),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = selectedTabColor
                    )
                }
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title, fontWeight = FontWeight.W500) },
                        selectedContentColor = selectedTabColor,
                        unselectedContentColor = Color.Gray,
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> {
                    ResidentsTab(residentResult, navController)
                }

                1 -> {
                    VisitorsTab(visitorsResult, navController)
                }
            }


        }

    }
}

@Composable
fun ResidentsTab(residentResult: ResidentResult?, navController: NavController) {
    Column {
        Text(
            text = "Moradores",
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
        when (residentResult) {
            is ResidentResult.Success -> {
                LazyColumn {
                    items(residentResult.data.size) {
                        ResidentCard(
                            onClick = {
                                navController.navigate("residentDetail/${residentResult.data[it].id}")
                            },
                            resident = residentResult.data[it]
                        )
                    }
                }
            }

            is ResidentResult.Error -> {
                Text(
                    text = "Erro ao carregar moradores",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            is ResidentResult.Loading -> {
                Text(
                    text = "Carregando moradores...",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun ResidentCard(onClick: () -> Unit, resident: Resident) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .height(80.dp)
                        .width(100.dp)

                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "Resident Icon"
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = resident.fullName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = formatCpf(resident.cpf),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = resident.phone,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = resident.email,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }

}

fun formatCpf(cpf: String): String {
    if (cpf.length != 11) {
        throw IllegalArgumentException("O CPF deve conter 11 dígitos.")
    }
    return "${cpf.substring(0, 3)}.***.***-${cpf.substring(9)}"
}

@Composable
fun VisitorsTab(visitorsResult: GetVisitorsResult?, navController: NavController) {
    Column {
        Text(
            text = "Visitantes",
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )

        when (visitorsResult) {
            is GetVisitorsResult.Success -> {
                LazyColumn {
                    items(visitorsResult.data.size) {
                        VisitorCard(
                            onClick = {
                                navController.navigate("visitorDetail/${visitorsResult.data[it].id}")
                            },
                            visitor = visitorsResult.data[it]
                        )
                    }
                }
            }

            is GetVisitorsResult.Error -> {
                Text(
                    text = "Erro ao carregar visitantes",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            is GetVisitorsResult.Loading -> {
                Text(
                    text = "Carregando visitantes...",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

