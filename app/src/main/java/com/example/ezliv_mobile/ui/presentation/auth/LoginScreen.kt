package com.example.ezliv_mobile.ui.presentation.auth

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ezliv_mobile.R
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.ezliv_mobile.ui.configurations.appModule
import com.example.ezliv_mobile.ui.presentation.apartamento.AddVisitorPage
import com.example.ezliv_mobile.ui.presentation.apartamento.ApartmentScreen
import com.example.ezliv_mobile.ui.presentation.apartamento.PersonalDataScreen
import com.example.ezliv_mobile.ui.presentation.apartamento.VisitorDetailScreen
import com.example.ezliv_mobile.ui.presentation.apartamento.view_model.ApartmentViewModel
import com.example.ezliv_mobile.ui.presentation.auth.result.LoginResult
import com.example.ezliv_mobile.ui.presentation.auth.view_model.AuthViewModel
import com.example.ezliv_mobile.ui.presentation.bills.BillsPage
import com.example.ezliv_mobile.ui.presentation.bills.view_model.BillsViewModel
import com.example.ezliv_mobile.ui.presentation.entregas.Entregas
import com.example.ezliv_mobile.ui.presentation.entregas.view_model.EntregasViewModel
import com.example.ezliv_mobile.ui.presentation.home.view_model.HomeViewModel
import com.example.ezliv_mobile.ui.presentation.home.MuralComponent
import com.example.ezliv_mobile.ui.presentation.reservas.pages.NewReservePage
import com.example.ezliv_mobile.ui.presentation.reservas.pages.ReservesPage
import com.example.ezliv_mobile.ui.presentation.reservas.view_model.ReserveViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController();

            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    val authViewModel by inject<AuthViewModel>();
                    Login(navController, authViewModel)
                }
                composable("mural") {
                    val homeViewModel by inject<HomeViewModel>();
                    homeViewModel.getUserById()

                    MuralComponent(navController, homeViewModel)
                }
                composable("password-change") {
                    val authViewModel by inject<AuthViewModel>();
                    RegisterPassword(navController, authViewModel)
                }
                composable("entregas") {
                    val entregasViewModel by inject<EntregasViewModel>();
                    entregasViewModel.getPackagesByApartment()

                    Entregas(navController, entregasViewModel)
                }
                composable("my-apartment") {
                    val apartmentViewModel by inject<ApartmentViewModel>();
                    apartmentViewModel.getResidents()
                    apartmentViewModel.getAllVisitors()
                    ApartmentScreen(navController, apartmentViewModel)
                }
                composable(
                    "residentDetail/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                ) {
                    val apartmentViewModel by inject<ApartmentViewModel>();
                    val backStackEntry = navController.currentBackStackEntryAsState()
                    val userId = backStackEntry.value?.arguments?.getString("userId") ?: ""
                    apartmentViewModel.getResidentById(userId)

                    PersonalDataScreen(navController, apartmentViewModel, isFirstFetch = true)
                }
                composable(
                    "visitorDetail/{visitorId}",
                    arguments = listOf(navArgument("visitorId") { type = NavType.StringType })
                ) {
                    val apartmentViewModel by inject<ApartmentViewModel>();
                    val backStackEntry = navController.currentBackStackEntryAsState()
                    val userId = backStackEntry.value?.arguments?.getString("visitorId") ?: ""
                    apartmentViewModel.getVisitorById(userId)

                    VisitorDetailScreen(navController, apartmentViewModel)
                }
                composable("addVisitor") {
                    val apartmentViewModel by inject<ApartmentViewModel>();
                    AddVisitorPage(navController, apartmentViewModel)
                }
                composable("reserves") {
                    val reserveViewModel by inject<ReserveViewModel>();
                    reserveViewModel.getReserves()
                    ReservesPage(navController, reserveViewModel)
                }
                composable("addReserve") {
                    val reserveViewModel by inject<ReserveViewModel>();
                    reserveViewModel.getCommonAreas()
                    NewReservePage(navController, reserveViewModel)
                }
                composable("bills") {
                    val billsViewModel by inject<BillsViewModel>();
                    BillsPage(navController, billsViewModel)
                }
            }
        }
        startKoin {
            androidContext(this@MainActivity)
            modules(
                appModule,
            )
        }
    }
}

@Composable
fun Login(navController: NavController, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf(value = "") }
    var senha by remember { mutableStateOf(value = "") }
    val result by authViewModel.result.observeAsState()

    var showErrorBottomSheet by remember { mutableStateOf(false) }

    if (result is LoginResult.Error && !showErrorBottomSheet) {
        showErrorBottomSheet = true
    }

    Scaffold {
        Box(Modifier.padding(it)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFF012A4A))
            ) {
                Logo()
                EmailTextField(email, onValueChange = { email = it })
                Spacer(modifier = Modifier.height(4.dp))
                PasswordTextField(senha, onValueChange = { senha = it })
                Spacer(modifier = Modifier.height(12.dp))
                if (result is LoginResult.Loading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                    )
                } else {
                    LoginButton(onClick = {
                        authViewModel.login(email, senha, navController = navController)
                    })
                }
                Spacer(modifier = Modifier.height(30.dp))
                ImageIconBottom()

                if (showErrorBottomSheet) {
                    AlertDialog(
                        onDismissRequest = {
                            showErrorBottomSheet = false
                            authViewModel.result.value = null
                        },
                        title = { Text("Erro ao fazer login") },
                        text = { Text("Verifique seu email e senha e tente novamente.") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showErrorBottomSheet = false
                                    authViewModel.result.value = null
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF001A2E),
                                    contentColor = Color.White,
                                ),
                            ) {
                                Text("Fechar", color = Color.White)
                            }
                        },
                        modifier = Modifier.padding(16.dp)
                    )

                }
            }
        }
    }

}

@Composable
fun Logo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp, top = 120.dp, start = 22.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.ezliv),
            contentDescription = "Logo"
        )
    }
}

@Composable
fun EmailTextField(email: String, onValueChange: (String) -> Unit) {

    TextField(
        value = email,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.MailOutline,
                contentDescription = "emailIcon",
            )
        },
        onValueChange = { it -> onValueChange(it) },
        label = { Text(text = "Email") },
        placeholder = { Text(text = "E-mail") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .clip(RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp))
            .width(340.dp)
            .height(60.dp)
    )
}

@Composable
fun PasswordTextField(senha: String, onValueChange: (String) -> Unit) {
    TextField(
        value = senha,
        visualTransformation = PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "passwordLock",
            )
        },
        onValueChange = { it -> onValueChange(it) },
        label = { Text(text = "Senha") },
        placeholder = { Text(text = "Senha") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier
            .clip(RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp))
            .width(340.dp)
            .height(100.dp)
            .padding(bottom = 40.dp)
    )
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF001A2E)),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(300.dp)
            .height(55.dp)
    ) {
        Text(text = "Entrar", fontSize = 20.sp)
    }
}

@Composable
fun ImageIconBottom() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.building),
            contentDescription = "Building",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(210.dp)
                .fillMaxSize()
                .align(Alignment.BottomEnd)
        )
    }
}


