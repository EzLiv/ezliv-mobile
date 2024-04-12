package com.example.ezliv_mobile.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezliv_mobile.ui.components.Aviso
import com.example.ezliv_mobile.ui.theme.EzlivmobileTheme

class Mural : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EzlivmobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Aviso()
                }
            }
        }
    }
}

@Composable
fun mural(

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    Cabecalho()
    Aviso()
    Aviso()
    rodape()

    }

}

@Composable
fun Cabecalho(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF012A4A))
            .height(65.dp)

    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Luis Gustavo Fantinato",
                style = TextStyle(color = Color.White),
                fontSize = 16.sp
            )
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "exit",
                tint = Color.White
            )

        }
    }
}

@Composable
fun rodape() {
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



@Preview(showBackground = true)
@Composable
fun Preview() {
    EzlivmobileTheme {
        mural()
    }
}