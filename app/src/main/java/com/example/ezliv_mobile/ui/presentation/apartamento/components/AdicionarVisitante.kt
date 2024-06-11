@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ezliv_mobile.ui.components

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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


@Composable
fun BoxVisitante() {
    Box(modifier = Modifier
        .background(Color.LightGray)
        .height(645.dp)
        .width(360.dp)
        .clip(shape = RoundedCornerShape(12.dp))) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Text(
                text = "Visitante",

                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color.LightGray,
                textAlign = TextAlign.Center,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 20.dp)
                    .background(Color(0xFF012A4A))

                    .height(70.dp)
                    .padding(top = 12.dp)




            )


            var nome by remember {
                mutableStateOf("")
            }
            Text(
                text = "Nome",
                color = Color(0xFF012A4A),
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 29.dp)
            )
            TextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .background(Color.White)
                    .height(55.dp)
                    .width(310.dp)

                    .align(Alignment.CenterHorizontally),

                value = nome,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xfffffff),
                    focusedContainerColor = Color.White
                ),
                onValueChange = {
                    nome = it
                },
                label = {

                },
                placeholder = {

                }
            )


            var email by remember {
                mutableStateOf("")
            }

            Text(
                text = "Email",
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                color = Color(0xFF012A4A),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(start = 29.dp)
            )
            TextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .height(55.dp)
                    .width(310.dp)
                    .background(Color.White)
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),

                value = email,

                onValueChange = {
                    email = it
                },
                label = {

                },
                placeholder = {

                })

            Spacer(modifier = Modifier.width(16.dp))
            var cpf by remember {
                mutableStateOf("")
            }

            Text(
                text = "CPF",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF012A4A),
                fontSize = 19.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(start = 29.dp)

            )
            TextField(
                modifier = Modifier
                    .height(55.dp)
                    .clip(RoundedCornerShape(7.dp))
                    .width(310.dp)
                    .background(Color.White)
                    .align(Alignment.CenterHorizontally),

                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xfffffff),
                    focusedContainerColor = Color.White
                ),
                value = cpf,
                onValueChange = {
                    cpf = it
                },
                label = {
                    Text("")
                },
                placeholder = {
                    Text(text = "")
                },
            )


            var sexo by remember {
                mutableStateOf("")
            }

            Text(
                text = "Sexo",
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                color = Color(0xFF012A4A),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(start = 32.dp)
            )
            var isExpandedSexo by remember { mutableStateOf(false) }
            var genderSexo by remember { mutableStateOf("") }

            Box(
                modifier = Modifier
                    .padding(start = 3.dp)
                    .align(Alignment.CenterHorizontally)

            ) {
                ExposedDropdownMenuBox(
                    expanded = isExpandedSexo,
                    onExpandedChange = { isExpandedSexo = it }
                ) {
                    TextField(
                        value = genderSexo,
                        onValueChange = {  },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedSexo)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .menuAnchor()
                            .clip(RoundedCornerShape(7.dp))
                            .height(55.dp)
                            .width(310.dp)
                            .background(Color.White)
                            .align(Alignment.Center)



                    )

                    DropdownMenu(
                        expanded = isExpandedSexo,
                        onDismissRequest = { isExpandedSexo = false },
                        modifier = Modifier.fillMaxWidth(),

                        ) {
                        DropdownMenuItem(
                            text = { Text(text = "Sexo:") },
                            onClick = {
                                genderSexo = "Sexo:"
                                isExpandedSexo = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text(text = "Masculino") },
                            onClick = {
                                genderSexo = "Masculino"
                                isExpandedSexo = false


                            })
                        DropdownMenuItem(
                            text = { Text(text = "Feminino") },
                            onClick = {
                                genderSexo = "Feminino"
                                isExpandedSexo = false
                            })
                    }
                }
            }


            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
            ) {

                Column {
                    var dataInicio by remember {
                        mutableStateOf("")
                    }

                    Text(
                        text = "Liberar Visitante de:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF012A4A),
                        textAlign = TextAlign.Start,
                        modifier = Modifier

                            .padding(top = 10.dp)
                            .padding(start = 33.dp, bottom = 4.dp)
                    )
                    var isExpandedDataInicio by remember { mutableStateOf(false) }
                    var genderDataInicio by remember { mutableStateOf("") }

                    Box(
                        modifier = Modifier
                            .padding(start = 9.dp)

                    ) {
                        ExposedDropdownMenuBox(
                            expanded = isExpandedDataInicio,
                            onExpandedChange = { isExpandedDataInicio = it }
                        ) {
                            TextField(
                                value = genderDataInicio,
                                onValueChange = {  },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedSexo)
                                },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White
                                ),
                                modifier = Modifier
                                    .menuAnchor()
                                    .clip(RoundedCornerShape(7.dp))
                                    .height(55.dp)
                                    .width(170.dp)
                                    .padding(start = 23.dp)

                                    .background(Color.White)
                                    .align(Alignment.Center)



                            )

                            DropdownMenu(
                                expanded = isExpandedDataInicio,
                                onDismissRequest = { isExpandedDataInicio= false },
                                modifier = Modifier.fillMaxWidth(),

                                ) {
                                DropdownMenuItem(
                                    text = { Text(text = " dia 1:") },
                                    onClick = {
                                        genderDataInicio = ""
                                        genderDataInicio = "dia 1"
                                        isExpandedDataInicio = false
                                    }
                                )

                                DropdownMenuItem(
                                    text = { Text(text = "Masculino") },
                                    onClick = {
                                        genderDataInicio = "Masculino"
                                        isExpandedDataInicio = false


                                    })
                                DropdownMenuItem(
                                    text = { Text(text = "Feminino") },
                                    onClick = {
                                        genderDataInicio = "Feminino"
                                        isExpandedDataInicio = false
                                    })
                            }
                        }
                    }
                }
                Column {
                    var dataFinal by remember {
                        mutableStateOf("")
                    }

                    Text(
                        text = "Até",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF012A4A),
                        textAlign = TextAlign.Start,
                        modifier = Modifier

                            .padding(top = 10.dp)
                            .padding(start = 33.dp, bottom = 4.dp)
                    )
                    var isExpandedDataFinal by remember { mutableStateOf(false) }
                    var genderDataFinal by remember { mutableStateOf("") }

                    Box(
                        modifier = Modifier
                            .padding(start = 9.dp)

                    ) {
                        ExposedDropdownMenuBox(
                            expanded = isExpandedDataFinal,
                            onExpandedChange = { isExpandedDataFinal = it }
                        ) {
                            TextField(
                                value = genderDataFinal,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedSexo)
                                },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White
                                ),
                                modifier = Modifier
                                    .menuAnchor()
                                    .clip(RoundedCornerShape(7.dp))
                                    .height(55.dp)
                                    .width(170.dp)
                                    .padding(start = 7.dp, end = 24.dp)

                                    .background(Color.White)
                                    .align(Alignment.Center)



                            )

                            DropdownMenu(
                                expanded = isExpandedDataFinal,
                                onDismissRequest = { isExpandedDataFinal= false },
                                modifier = Modifier.fillMaxWidth(),

                                ) {
                                DropdownMenuItem(
                                    text = { Text(text = " dia 1:") },
                                    onClick = {
                                        genderDataFinal = ""
                                        genderDataFinal = "dia 1"
                                        isExpandedDataFinal = false
                                    }
                                )

                                DropdownMenuItem(
                                    text = { Text(text = "Masculino") },
                                    onClick = {
                                        genderDataFinal = "Masculino"
                                        isExpandedDataFinal = false


                                    })
                                DropdownMenuItem(
                                    text = { Text(text = "Feminino") },
                                    onClick = {
                                        genderDataFinal = "Feminino"
                                        isExpandedDataFinal = false
                                    })
                            }
                        }
                    }
                }
            }

            Button(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .width(140.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(50.dp)
                    .background(Color(0xFF012A4A))

                ,

                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                ),
                onClick = { /*TODO*/ }) {
                Text(text = "Salvar", modifier = Modifier,
                    color = Color.White,
                    fontSize = 22.sp,

                    )

            }
        }

    }}




@Preview
@Composable
fun BoxVisitantePreview(){
    BoxVisitante()
}
