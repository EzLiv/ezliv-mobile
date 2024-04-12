package com.example.ezliv_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(

//    routes: List<Route>,
//    onItemClicked: (Route) -> Unit
) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFF012A4A))
        .height(60.dp), verticalAlignment = Alignment.CenterVertically
        , horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
        Icon(imageVector = Icons.Filled.Home, contentDescription = "Inicio  " ,tint = Color.White)
            Text(text = "Inicio",color = Color.White, fontWeight = FontWeight.Bold)
        }
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = Icons.Filled.Email, contentDescription = "Entregas" , tint = Color.White)
            Text(text = "Entregas",color = Color.White, fontWeight = FontWeight.Bold)
        }
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Icon(imageVector = Icons.Filled.Person, contentDescription = "Apartamento" ,tint = Color.White )
            Text(text = "Apartamento",color = Color.White, fontWeight = FontWeight.Bold)
        }

        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Reservas" ,tint = Color.White)
        Text(text = "Reservas", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun AppBarPreview() {
    AppBar()
}