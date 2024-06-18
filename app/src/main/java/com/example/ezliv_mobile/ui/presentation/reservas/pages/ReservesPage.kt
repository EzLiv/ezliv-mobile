package com.example.ezliv_mobile.ui.presentation.reservas.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ezliv_mobile.ui.presentation.reservas.components.ReserveCard
import com.example.ezliv_mobile.ui.presentation.reservas.results.DeleteReserveResult
import com.example.ezliv_mobile.ui.presentation.reservas.results.GetReservesResult
import com.example.ezliv_mobile.ui.presentation.reservas.view_model.ReserveViewModel
import com.example.ezliv_mobile.ui.presentation.ui.components.AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservesPage(navController: NavController, reserveViewModel: ReserveViewModel) {
    val getAllReserves by reserveViewModel.getAllReservesResult.observeAsState()
    val deleteReserve by reserveViewModel.deleteReserveResult.observeAsState()

    var reserveToDeleteId by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

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
                        text = "Reservas",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                    )
                }
            },
        )
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("addReserve")
                },
                containerColor = Color(0xFF012A4A),
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Reserve")
            }

        },
        bottomBar = {
            AppBar(navController)
        }) {
        Column(
            Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            Text(text = "Minhas Reservas", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            when (getAllReserves) {
                is GetReservesResult.Success -> {
                    val reserves = (getAllReserves as GetReservesResult.Success).data
                    LazyColumn(content = {
                        items(reserves.size) { index ->
                            ReserveCard(reserveModel = reserves[index],
                                onDeleteClicked = {
                                    reserveToDeleteId = reserves[index].id
                                    showDialog = true
                                })
                        }
                    })
                }

                is GetReservesResult.Loading -> {
                    CircularProgressIndicator(
                        color = Color(0xFF012A4A),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                is GetReservesResult.Error -> {
                    Text(
                        text = (getAllReserves as GetReservesResult.Error).message,
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            }

            DeleteReserveDialog(showDialog = showDialog, onConfirm = {
                reserveViewModel.deleteReserve(reserveToDeleteId)
                showDialog = false
                reserveViewModel.getReserves()
            }, onDismiss = {
                showDialog = false
            })
        }
    }
}

@Composable
fun DeleteReserveDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Confirmar Exclusão") },
            text = { Text("Tem certeza que deseja excluir esta reserva?") },
            confirmButton = {
                Button(
                    onClick = { onConfirm() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF012A4A))
                ) {
                    Text("Confirmar", color = Color.White)
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFF012A4A))
                ) {
                    Text("Cancelar", color = Color(0xFF012A4A))
                }
            }
        )
    }
}



