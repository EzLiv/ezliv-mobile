package com.example.ezliv_mobile.ui.presentation.apartamento

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ezliv_mobile.ui.domain.apartment.models.NewVisitorModel
import com.example.ezliv_mobile.ui.presentation.apartamento.components.CreateDateInput
import com.example.ezliv_mobile.ui.presentation.apartamento.components.CreateUserInput
import com.example.ezliv_mobile.ui.presentation.apartamento.components.toApiFormat
import com.example.ezliv_mobile.ui.presentation.apartamento.results.CreateVisitorResult
import com.example.ezliv_mobile.ui.presentation.apartamento.view_model.ApartmentViewModel
import com.example.ezliv_mobile.ui.presentation.ui.helpers.isValidEmail
import com.example.ezliv_mobile.ui.presentation.ui.helpers.isValidName
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVisitorPage(navController: NavController, apartmentViewModel: ApartmentViewModel) {
    var name by remember { mutableStateOf("") }
    var releaseDate by remember { mutableStateOf(LocalDateTime.now()) }
    var untilDate by remember { mutableStateOf(LocalDateTime.now().plusDays(1)) }
    var email by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }

    var hasErrorName by remember { mutableStateOf(false) }
    var hasErrorEmail by remember { mutableStateOf(false) }
    var hasErrorCpf by remember { mutableStateOf(false) }
    var hasErrorDates by remember { mutableStateOf(false) }

    val createUserResult by apartmentViewModel.createVisitorResult.observeAsState()

    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF012A4A),
            ),
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(end = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Adicionar visitante",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                    )
                }
            },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
            }
        )
    }) {
        Column(
            Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            Text(
                "Adicionar visitante",
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CreateUserInput(
                label = "Nome do visitante",
                isError = hasErrorName,
                onValueChange = {
                    name = it
                    hasErrorName = !isValidName(name)
                },
                text = name,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CreateUserInput(
                label = "E-mail",
                isError = hasErrorEmail,
                onValueChange = {
                    email = it
                    hasErrorEmail = !isValidEmail(email)
                },
                text = email,
                keyBoardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            CreateUserInput(
                label = "CPF",
                isError = hasErrorCpf,
                onValueChange = {
                    cpf = it
                    hasErrorCpf = it.length != 11
                },
                text = cpf,
                keyBoardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(16.dp))
            CreateDateInput(
                initialDateTime = LocalDateTime.now(),
                onDateTimeSelected = {
                    releaseDate = it
                    hasErrorDates = untilDate.isBefore(releaseDate)
                },
                onCancel = {},
                label = "Liberar de:",
                hasError = hasErrorDates
            )
            Spacer(modifier = Modifier.height(16.dp))
            CreateDateInput(
                initialDateTime = LocalDateTime.now().plusDays(1),
                onDateTimeSelected = {
                    untilDate = it
                    hasErrorDates = untilDate.isBefore(releaseDate)
                },
                onCancel = {},
                label = "Até:",
                hasError = hasErrorDates
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (createUserResult is CreateVisitorResult.Loading) {
                CircularProgressIndicator(
                    color = Color(0xFF012A4A),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            } else {
                OutlinedButton(
                    enabled = !hasErrorName && !hasErrorEmail && !hasErrorCpf && !hasErrorDates,
                    border = BorderStroke(
                        1.dp,
                        if (!hasErrorName && !hasErrorEmail && !hasErrorCpf && !hasErrorDates) Color(
                            0xFF012A4A
                        ) else Color.Gray
                    ),
                    onClick = {
                        apartmentViewModel.createVisitor(
                            NewVisitorModel(
                                name = name,
                                email = email,
                                cpf = cpf,
                                releaseDate = releaseDate.toApiFormat(),
                                untilDate = untilDate.toApiFormat()
                            )
                        )
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Adicionar",
                        color = if (!hasErrorName && !hasErrorEmail && !hasErrorCpf && !hasErrorDates) Color(
                            0xFF012A4A
                        ) else Color.Gray,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                    )
                }
            }
        }
    }
}
