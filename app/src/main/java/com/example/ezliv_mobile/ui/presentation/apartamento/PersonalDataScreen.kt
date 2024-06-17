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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ezliv_mobile.ui.domain.apartment.models.UpdateResidentModel
import com.example.ezliv_mobile.ui.presentation.apartamento.results.GetResidentByIdResult
import com.example.ezliv_mobile.ui.presentation.apartamento.results.ResidentResult
import com.example.ezliv_mobile.ui.presentation.apartamento.view_model.ApartmentViewModel
import com.example.ezliv_mobile.ui.presentation.ui.components.CustomInput
import com.example.ezliv_mobile.ui.presentation.ui.helpers.PhoneVisualTransformation
import com.example.ezliv_mobile.ui.presentation.ui.helpers.formatarData
import com.example.ezliv_mobile.ui.presentation.ui.helpers.isValidEmail
import com.example.ezliv_mobile.ui.presentation.ui.helpers.isValidName
import com.example.ezliv_mobile.ui.presentation.ui.helpers.isValidPhone

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataScreen(navController: NavController, apartmentViewModel: ApartmentViewModel, isFirstFetch : Boolean = false) {
    var cpf by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

    var editedName by remember { mutableStateOf("") }
    var editedPhone by remember { mutableStateOf("") }
    var editedEmail by remember { mutableStateOf("") }

    var isErrorName by remember { mutableStateOf(false) }
    var isErrorPhone by remember { mutableStateOf(false) }
    var isErrorEmail by remember { mutableStateOf(false) }


    val user by apartmentViewModel.residentByIdResult.observeAsState()

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
                        text = "Dados pessoais",
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
                "Meus dados",
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(16.dp)
            )
            when (user) {
                is GetResidentByIdResult.Success -> {
                    val resident = (user as GetResidentByIdResult.Success).data
                    if(cpf.isEmpty()) cpf = resident?.cpf ?: ""
                    if(name.isEmpty()) name = resident?.fullName ?: ""
                    if(phone.isEmpty()) phone = resident?.phone ?: ""
                    if(email.isEmpty()) email = resident?.email ?: ""
                    birthDate = resident?.birthDate ?: ""

                    if (editedName.isEmpty() && !isErrorName) editedName = name
                    if (editedPhone.isEmpty() && !isErrorPhone) editedPhone = phone
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
                            isEditable = true,
                            text = editedName,
                            onValueChange = {
                                editedName = it
                                isErrorName = !isValidName(it)
                            },
                            visualTransformation = null,
                            onEdited = {
                                if (!isErrorName) {
                                    apartmentViewModel.updateResident(
                                        resident?.id ?: "",
                                        UpdateResidentModel(
                                            fullName = editedName, phone = null, email = null
                                        )
                                    )
                                }
                            },
                            onCancelEdit = {
                                editedName = name
                                isErrorName = false
                            },
                            isError = isErrorName
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomInput(
                            label = "Telefone",
                            isEditable = true,
                            text = editedPhone,
                            onValueChange = {
                                isErrorPhone = !isValidPhone(it)
                                editedPhone = it
                            },
                            visualTransformation = PhoneVisualTransformation(),
                            onEdited = {
                                if (!isErrorPhone) {
                                    apartmentViewModel.updateResident(
                                        resident?.id ?: "",
                                        UpdateResidentModel(
                                            fullName = null, phone = editedPhone, email = null
                                        )
                                    )
                                }
                            },
                            onCancelEdit = {
                                editedPhone = phone
                                isErrorPhone = false
                            },
                            isError = isErrorPhone
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
                                    apartmentViewModel.updateResident(
                                        resident?.id ?: "",
                                        UpdateResidentModel(
                                            fullName = null, phone = null, email = editedEmail
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
                        CustomInput(
                            label = "Data de nascimento",
                            isEditable = false,
                            text = formatarData(birthDate),
                            onValueChange = {
                                birthDate = it
                            },
                            visualTransformation = null,
                            onEdited = {},
                            onCancelEdit = {}
                        )
                    }
                }

                is GetResidentByIdResult.Error -> {
                    Text(
                        text = "Erro ao buscar dados do usuário",
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                is GetResidentByIdResult.Loading -> {
                    Text(
                        text = "Carregando dados...",
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }


        }
    }
}
