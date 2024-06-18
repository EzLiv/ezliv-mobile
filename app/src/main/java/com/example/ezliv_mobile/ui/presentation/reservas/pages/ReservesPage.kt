package com.example.ezliv_mobile.ui.presentation.reservas.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ezliv_mobile.ui.presentation.reservas.view_model.ReserveViewModel
import com.example.ezliv_mobile.ui.presentation.ui.components.AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservesPage(navController: NavController, reserveViewModel: ReserveViewModel) {
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
        Column(Modifier.padding(it)) {

        }
    }
}



