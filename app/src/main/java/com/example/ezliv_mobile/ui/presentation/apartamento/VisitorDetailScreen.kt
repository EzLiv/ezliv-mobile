package com.example.ezliv_mobile.ui.presentation.apartamento

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ezliv_mobile.ui.domain.apartment.models.VisitorUpdateModel
import com.example.ezliv_mobile.ui.presentation.apartamento.components.CustomizedDateTimePicker
import com.example.ezliv_mobile.ui.presentation.apartamento.components.DeleteUserDialog
import com.example.ezliv_mobile.ui.presentation.apartamento.components.toApiFormat
import com.example.ezliv_mobile.ui.presentation.apartamento.components.toFormattedString
import com.example.ezliv_mobile.ui.presentation.apartamento.components.toLocalDateTime
import com.example.ezliv_mobile.ui.presentation.apartamento.results.GetVisitorByIdResult
import com.example.ezliv_mobile.ui.presentation.apartamento.view_model.ApartmentViewModel
import com.example.ezliv_mobile.ui.presentation.ui.components.CustomInput

import com.example.ezliv_mobile.ui.presentation.ui.helpers.isValidEmail
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitorDetailScreen(navController: NavController, apartmentViewModel: ApartmentViewModel) {
    var cpf by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var untilDate by remember { mutableStateOf("") }
    var releaseDate by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var editingReleaseDate by remember { mutableStateOf(false) }
    var editingUntilDate by remember { mutableStateOf(false) }

    var editedReleaseDate by remember { mutableStateOf("") }
    var editedUntilDate by remember { mutableStateOf("") }
    var editedEmail by remember { mutableStateOf("") }

    var isErrorUntilDate by remember { mutableStateOf(false) }
    var isErrorReleaseDate by remember { mutableStateOf(false) }
    var isErrorEmail by remember { mutableStateOf(false) }


    val user by apartmentViewModel.getVisitorsByIdResult.observeAsState()

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
                        .padding(end = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Dados do visitante",
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
    }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            Text(
                "Meus dados",
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(16.dp)
            )
            when (user) {
                is GetVisitorByIdResult.Success -> {
                    val visitor = (user as GetVisitorByIdResult.Success).data
                    if (cpf.isEmpty()) cpf = visitor?.cpf ?: ""
                    if (name.isEmpty()) name = visitor?.name ?: ""
                    if (email.isEmpty()) email = visitor?.email ?: ""
                    if (untilDate.isEmpty()) untilDate = visitor?.untilDate ?: ""
                    if (releaseDate.isEmpty()) releaseDate = visitor?.releaseDate ?: ""

                    if (editedReleaseDate.isEmpty() && !isErrorReleaseDate) editedReleaseDate =
                        releaseDate
                    if (editedUntilDate.isEmpty() && !isErrorUntilDate) editedUntilDate = untilDate
                    if (editedEmail.isEmpty() && !isErrorEmail) editedEmail = email

                    Column {
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomInput(
                            label = "CPF",
                            isEditable = false,
                            text = formatCpf(cpf),
                            onValueChange = { cpf = it },
                            visualTransformation = null,
                            onEdited = {},
                            onCancelEdit = {}
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomInput(
                            label = "Nome",
                            isEditable = false,
                            text = name,
                            onValueChange = {
                                name = it
                            },
                            visualTransformation = null,
                            onEdited = {},
                            onCancelEdit = {},
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        CustomInput(
                            label = "E-mail",
                            isEditable = true,
                            text = editedEmail,
                            onValueChange = {
                                isErrorEmail = !isValidEmail(it)
                                editedEmail = it
                            },
                            visualTransformation = null,
                            onEdited = {
                                if (!isErrorEmail) {
                                    apartmentViewModel.updateVisitorById(
                                        visitor?.id ?: "",
                                        VisitorUpdateModel(
                                            email = editedEmail,
                                            releaseDate = null,
                                            untilDate = null,
                                        )
                                    )
                                }
                            },
                            onCancelEdit = {
                                editedEmail = email
                                isErrorEmail = false
                            },
                            isError = isErrorEmail
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomizedDateTimePicker(
                            initialDateTime = editedReleaseDate.toLocalDateTime(),
                            onDateTimeSelected = {
                                editedReleaseDate = it.toFormattedString()
                                editingReleaseDate = false
                                apartmentViewModel.updateVisitorById(
                                    visitor?.id ?: "",
                                    VisitorUpdateModel(
                                        email = null,
                                        releaseDate = it.toApiFormat(),
                                        untilDate = null,
                                    )
                                )
                            },
                            editing = editingReleaseDate,
                            changeEditing = {
                                editingReleaseDate = it
                            },
                            onCancel = {
                                editedReleaseDate = releaseDate
                                editingReleaseDate = false
                            },
                            label = "Liberado de:"
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomizedDateTimePicker(
                            initialDateTime = editedUntilDate.toLocalDateTime(),
                            onDateTimeSelected = {
                                editedUntilDate = it.toFormattedString()
                                editingUntilDate = false
                                apartmentViewModel.updateVisitorById(
                                    visitor?.id ?: "",
                                    VisitorUpdateModel(
                                        email = null,
                                        releaseDate = null,
                                        untilDate = it.toApiFormat(),
                                    )
                                )
                            },
                            editing = editingUntilDate,
                            changeEditing = {
                                editingUntilDate = it
                            },
                            onCancel = {
                                editedUntilDate = releaseDate
                                editingUntilDate = false
                            },
                            label = "Até:"
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        var showDialog by remember { mutableStateOf(false) }


                        Column(modifier = Modifier.clickable {
                            showDialog = true
                        }.fillMaxWidth()) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "",
                                tint = Color.Red,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Excluir visitante",
                                color = Color.Red,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }

                        DeleteUserDialog(
                            showDialog = showDialog,
                            onConfirm = {
                                showDialog = false
                                apartmentViewModel.deleteVisitorById(visitor?.id ?: "")
                                navController.popBackStack()
                            },
                            onDismiss = { showDialog = false }
                        )

                    }
                }

                is GetVisitorByIdResult.Error -> {
                    Text(
                        text = "Erro ao buscar dados do morador",
                        color = Color.Red,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                is GetVisitorByIdResult.Loading -> {
                    Text(
                        text = "Carregando dados...",
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }

        }
    }
}