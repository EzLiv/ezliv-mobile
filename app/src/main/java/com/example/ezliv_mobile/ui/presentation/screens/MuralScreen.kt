package com.example.ezliv_mobile.ui.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ezliv_mobile.ui.data.result.GetUserResult
import com.example.ezliv_mobile.ui.data.result.NoticesResult
import com.example.ezliv_mobile.ui.data.view_models.HomeViewModel
import com.example.ezliv_mobile.ui.presentation.components.Aviso
import com.example.ezliv_mobile.ui.presentation.theme.EzlivmobileTheme

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

                }
            }
        }
    }
}

@Composable
fun MuralComponent(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val result by homeViewModel.result.observeAsState()

    val noticesResult by homeViewModel.noticesResult.observeAsState()

    var fullName = ""

    when (result) {
        is GetUserResult.Loading -> {
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

        is GetUserResult.Error -> {
            navController.popBackStack("login", inclusive = false)
        }
        else -> {
            fullName = (result as GetUserResult.Success).user.fullName
            homeViewModel.getNotices()
            Scaffold(topBar = {
                Cabecalho(fullName, onClickExit = {
                    homeViewModel.logout()
                    navController.popBackStack("login", inclusive = false)
                })
            },
                bottomBar = {
                    Rodape()
                }
            ) {
                when (noticesResult) {
                    is NoticesResult.Loading -> {
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
                    is NoticesResult.Error -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Erro ao carregar avisos",
                                    style = TextStyle(color = Color.Black),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    else -> {
                        val notices = (noticesResult as NoticesResult.Success).data
                        Box(modifier = Modifier.padding(it)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                            ) {
                                LazyColumn(
                                    modifier = Modifier.padding(10.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    items(notices.size) { index ->
                                        Aviso(
                                            title = notices[index].title,
                                            description = notices[index].description,
                                            createdAt = notices[index].createdAt,
                                            name = notices[index].condominiumName,
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


}

@Composable
fun Cabecalho(userName: String, onClickExit: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF012A4A))
            .height(65.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = userName,
                style = TextStyle(color = Color.White),
                fontSize = 16.sp
            )
            Icon(
                modifier = Modifier.clickable { onClickExit() },
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "exit",
                tint = Color.White

            )

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
