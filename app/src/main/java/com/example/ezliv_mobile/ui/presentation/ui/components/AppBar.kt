package com.example.ezliv_mobile.ui.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBar(
    navController: NavController,
//    routes: List<Route>,
//    onItemClicked: (Route) -> Unit
) {
    val currentRoute by navController.currentBackStackEntryAsState()
    val currentRouteName = currentRoute?.destination?.route


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF012A4A))
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        AppBarItem(
            navController = navController,
            currentRoute = currentRouteName,
            route = "mural",
            icon = Icons.Filled.Home,
            text = "Inicio"
        )
        AppBarItem(
            navController = navController,
            currentRoute = currentRouteName,
            route = "entregas",
            icon = Icons.Filled.Email,
            text = "Entregas"
        )
        AppBarItem(
            navController = navController,
            currentRoute = currentRouteName,
            route = "my-apartment",
            icon = Icons.Filled.Person,
            text = "Apartamento"
        )
        AppBarItem(
            navController = navController,
            currentRoute = currentRouteName,
            route = "reserves",
            icon = Icons.Filled.DateRange,
            text = "Reservas"
        )
        AppBarItem(
            navController = navController,
            currentRoute = currentRouteName,
            route = "bills",
            icon = Icons.Filled.MailOutline,
            text = "Contas"
        )
    }
}

@Composable
fun AppBarItem(
    navController: NavController,
    currentRoute: String?,
    route: String,
    icon: ImageVector,
    text: String
) {
    val isSelected = currentRoute == route
    val color = if (isSelected) Color.White else Color.Gray

    Column(
        modifier = Modifier.clickable { navController.navigate(route) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = color
        )
        Text(text = text, color = color, fontWeight = FontWeight.Bold)
    }
}

