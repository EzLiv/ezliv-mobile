package com.example.ezliv_mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxReserva() {

    Box(modifier = Modifier
        .background(color = Color(0xFFD9D9D9))
        .height(465.dp)
        .width(360.dp)
        .clip(shape = RoundedCornerShape(12.dp))) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Text(
                text = "Reserva",

                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color(0xFF012A4A),
                textAlign = TextAlign.Center,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 20.dp)
                    .background(color = Color(0xFFCDCBCB))

                    .height(70.dp)
                    .padding(top = 12.dp)


            )


            var area by remember {
                mutableStateOf("")
            }
            Text(
                text = "Área",
                color = Color(0xFF012A4A),
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 45.dp)
            )

            var isExpanded by remember { mutableStateOf(false) }
            var gender by remember { mutableStateOf("") }

            Box(
                modifier = Modifier
                    .padding(start = 9.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it }
                ) {
                    TextField(
                        value = gender,
                        onValueChange = { /* Aqui você pode adicionar a lógica para alterar o valor */ },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(Color.Transparent),
                        modifier = Modifier
                            .menuAnchor()
                            .clip(RoundedCornerShape(7.dp))
                            .height(60.dp)
                            .width(310.dp)
                            .align(Alignment.Center)
                            .padding(start = 29.dp)

                    )

                    DropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false },
                        modifier = Modifier.fillMaxWidth(),

                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Area") },
                            onClick = {
                                gender = " Area"
                                isExpanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text(text = "Salão de festas - Torre Norte") },
                            onClick = {
                                gender = " Salão de festas - Torre Norte"
                                isExpanded = false


                            })
                        DropdownMenuItem(
                            text = { Text(text = "Salão de festas - Torre Sul") },
                            onClick = {
                                gender = " Salão de festas - Torre Sul"
                                isExpanded = false
                            })
                    }
                }
            }


            var data by remember {
                mutableStateOf("")
            }

            Text(
                text = "Data",
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                color = Color(0xFF012A4A),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(start = 45.dp)
            )
            var isExpandedData by remember { mutableStateOf(false) }
            var genderData by remember { mutableStateOf("") }

            Box(
                modifier = Modifier
                    .padding(start = 9.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = isExpandedData,
                    onExpandedChange = { isExpandedData = it }
                ) {
                    TextField(
                        value = genderData,
                        onValueChange = { /* Aqui você pode adicionar a lógica para alterar o valor */ },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedData)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(Color.Transparent),
                        modifier = Modifier
                            .menuAnchor()
                            .clip(RoundedCornerShape(7.dp))
                            .height(60.dp)
                            .width(310.dp)
                            .align(Alignment.Center)
                            .padding(start = 29.dp)

                    )

                    DropdownMenu(
                        expanded = isExpandedData,
                        onDismissRequest = { isExpandedData = false },
                        modifier = Modifier.fillMaxWidth(),

                        ) {
                        DropdownMenuItem(
                            text = { Text(text = "Data") },
                            onClick = {
                                genderData = "Data"
                                isExpandedData = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text(text = "05/05/2024") },
                            onClick = {
                                genderData = "05/05/2024"
                                isExpandedData = false


                            })
                        DropdownMenuItem(
                            text = { Text(text = "13/05/2024") },
                            onClick = {
                                genderData = "13/05/2024"
                                isExpandedData = false
                            })
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))
            var hora by remember {
                mutableStateOf("")
            }

            Text(
                text = "Hora",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF012A4A),
                fontSize = 19.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(start = 45.dp)

            )
            var isExpandedHora by remember { mutableStateOf(false) }
            var genderHora by remember { mutableStateOf("") }

            Box(
                modifier = Modifier
                    .padding(start = 9.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = isExpandedHora,
                    onExpandedChange = { isExpandedHora = it }
                ) {
                    TextField(
                        value = genderHora,
                        onValueChange = {  },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedHora)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(Color.Transparent),
                        modifier = Modifier
                            .menuAnchor()
                            .clip(RoundedCornerShape(7.dp))
                            .height(60.dp)
                            .width(310.dp)
                            .align(Alignment.Center)
                            .padding(start = 29.dp)

                    )

                    DropdownMenu(
                        expanded = isExpandedHora,
                        onDismissRequest = { isExpandedHora = false },
                        modifier = Modifier.fillMaxWidth(),

                        ) {
                        DropdownMenuItem(
                            text = { Text(text = "Hora") },
                            onClick = {
                                genderHora = "Hora"
                                isExpandedHora = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text(text = "10:00 - 14:00") },
                            onClick = {
                                genderHora = "10:00 - 14:00"
                                isExpandedHora = false


                            })
                        DropdownMenuItem(
                            text = { Text(text = "19:30 - 21:30") },
                            onClick = {
                                genderHora = "19:30 - 21:30"
                                isExpandedHora = false
                            })
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .width(140.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(50.dp)
                    .background(color = Color(0xFF012A4A)),

                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                ),
                onClick = {  }) {
                Text(
                    text = "Salvar", modifier = Modifier,
                    color = Color.White,
                    fontSize = 22.sp,

                    )

            }
        }

    }


}

@Preview
@Composable
fun BoxReservaPreview(){
    BoxReserva()
}
