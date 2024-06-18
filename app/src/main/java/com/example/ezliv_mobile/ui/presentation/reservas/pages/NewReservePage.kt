package com.example.ezliv_mobile.ui.presentation.reservas.pages

import android.app.TimePickerDialog
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ezliv_mobile.ui.domain.apartment.models.NewVisitorModel
import com.example.ezliv_mobile.ui.domain.reserves.model.CommonAreaModel
import com.example.ezliv_mobile.ui.domain.reserves.model.NewReserveModel
import com.example.ezliv_mobile.ui.presentation.apartamento.components.toApiFormat
import com.example.ezliv_mobile.ui.presentation.apartamento.components.toFormattedString
import com.example.ezliv_mobile.ui.presentation.apartamento.components.toLocalTime
import com.example.ezliv_mobile.ui.presentation.apartamento.results.CreateVisitorResult
import com.example.ezliv_mobile.ui.presentation.entregas.view_model.EntregasViewModel
import com.example.ezliv_mobile.ui.presentation.reservas.results.CreateReserveResult
import com.example.ezliv_mobile.ui.presentation.reservas.results.GetCommonAreasResult
import com.example.ezliv_mobile.ui.presentation.reservas.view_model.ReserveViewModel
import java.sql.Time
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewReservePage(navController: NavController, reserveViewModel: ReserveViewModel) {
    val commonAreasResult by reserveViewModel.getCommonAreasResult.observeAsState()

    val reserveAreasResult by reserveViewModel.createReserveResult.observeAsState()

    var selectedCommonArea by remember { mutableStateOf<CommonAreaModel?>(null) }

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    var selectedTime by remember { mutableStateOf(LocalTime.now()) }

    var selectedeEndTime by remember { mutableStateOf(LocalTime.now().plusHours(1)) }

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
                        text = "Nova Reserva",
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
            when (commonAreasResult) {
                is GetCommonAreasResult.Loading -> {
                    Text(text = "Carregando...")
                }

                is GetCommonAreasResult.Error -> {
                    Text(text = "Error: ${(commonAreasResult as GetCommonAreasResult.Error).message}")
                }

                is GetCommonAreasResult.Success -> {
                    val commonAreas = (commonAreasResult as GetCommonAreasResult.Success).data
                    selectedCommonArea = commonAreas[0]
                    Text(text = "Nova Reserva", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomSelect(commonAreas, onSelected = { commonArea ->
                        // Do something with the selected common area
                    })
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomizedDateInput(
                        initialDateTime = selectedDate,
                        onDateTimeSelected = { date ->
                            selectedDate = date
                        },
                        onCancel = {
                            selectedDate = LocalDate.now()
                        },
                        label = "Data"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomizedTimeInput(
                        initialTime = selectedTime,
                        onTimeSelected = { time ->
                            selectedTime = time
                        },
                        onCancel = {
                            selectedTime = LocalTime.now()
                        },
                        label = "Inicio",
                        minTime = selectedCommonArea?.openingTime?.toLocalTime() ?: LocalTime.now(),
                        maxTime = selectedCommonArea?.closingTime?.toLocalTime() ?: LocalTime.now()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomizedTimeInput(
                        initialTime = selectedeEndTime,
                        onTimeSelected = { time ->
                            selectedeEndTime = time
                        },
                        onCancel = {
                            selectedeEndTime = LocalTime.now().plusHours(1)
                        },
                        label = "Fim",
                        minTime = selectedCommonArea?.openingTime?.toLocalTime() ?: LocalTime.now(),
                        maxTime = selectedCommonArea?.closingTime?.toLocalTime() ?: LocalTime.now()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (reserveAreasResult is CreateReserveResult.Loading) {
                        CircularProgressIndicator(
                            color = Color(0xFF012A4A),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp)
                        )
                    } else {
                        OutlinedButton(
                            border = BorderStroke(
                                1.dp,
                                Color(
                                    0xFF012A4A
                                )
                            ),
                            onClick = {
                                reserveViewModel.createReserve(
                                    selectedCommonArea?.id ?: "",
                                    NewReserveModel(
                                        date = selectedDate.toApiFormat(),
                                        startDateTime = selectedTime.toApiFormat(),
                                        endDateTime = selectedeEndTime.toApiFormat()
                                    )
                                )
                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "Reservar",
                                color = Color(
                                    0xFF012A4A
                                ),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CustomSelect(commonAreas: List<CommonAreaModel>, onSelected: (CommonAreaModel) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf<CommonAreaModel?>(commonAreas[0]) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Área Comum", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = selectedOption?.name ?: "Selecione uma opção",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                fontSize = 16.sp
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Arrow Down",
                tint = Color.Black,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            commonAreas.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(text = option.name, fontSize = 16.sp)
                    },
                    onClick = {
                        onSelected(option)
                        selectedOption = option
                        expanded = false
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomizedDateInput(
    initialDateTime: LocalDate,
    onDateTimeSelected: (LocalDate) -> Unit,
    onCancel: () -> Unit,
    label: String
) {
    var selectedDateTime by remember { mutableStateOf(initialDateTime) }

    val context = LocalContext.current

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val newDateTime = LocalDate.of(year, month + 1, dayOfMonth)
            selectedDateTime = newDateTime
            onDateTimeSelected(newDateTime)
        },
        selectedDateTime.year,
        selectedDateTime.monthValue - 1,
        selectedDateTime.dayOfMonth
    ).apply {
        setOnCancelListener {
            onCancel()
        }
    }

    Column {
        Box {
            Text(
                text = label,
                color = Color.Black,
                fontSize = 16.sp,
            )
        }

        Row {
            Text(
                text = selectedDateTime.toFormattedString(),
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Date",
                modifier = Modifier
                    .clickable {
                        datePickerDialog.show()
                    }
                    .align(Alignment.CenterVertically)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomizedTimeInput(
    initialTime: LocalTime,
    onTimeSelected: (LocalTime) -> Unit,
    onCancel: () -> Unit,
    label: String,
    minTime: LocalTime,
    maxTime: LocalTime,
) {
    var selectedTime by remember { mutableStateOf(initialTime) }

    val context = LocalContext.current

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            val newTime = LocalTime.of(hourOfDay, minute)
            if (newTime.isAfter(minTime) && newTime.isBefore(maxTime)) {
                selectedTime = newTime
                onTimeSelected(newTime)
            } else {
                Toast.makeText(context, "Hora fora do intervalo permitido", Toast.LENGTH_SHORT)
                    .show()
            }
        },
        selectedTime.hour,
        selectedTime.minute,
        true
    ).apply {
        setOnCancelListener {
            onCancel()
        }
    }

    Column {
        Box {
            Text(
                text = label,
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
        }

        Row {
            Text(
                text = selectedTime.toFormattedString(),
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Time",
                modifier = Modifier
                    .clickable {
                        timePickerDialog.show()
                    }
                    .align(Alignment.CenterVertically)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )
    }
}


