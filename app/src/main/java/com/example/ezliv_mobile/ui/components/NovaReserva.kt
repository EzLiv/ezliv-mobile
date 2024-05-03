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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
fun BoxReserva() {

    Box(modifier = Modifier
        .background(color = Color(0xFFD9D9D9))
        .height(455.dp)
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
                    .padding(start = 29.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .background(color = Color(0xFFCDCBCB))
                    .height(55.dp)
                    .width(310.dp)

                    .align(Alignment.CenterHorizontally),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Aréa",
                        modifier = Modifier.size(50.dp)
                    )

                },
                value = area,

                onValueChange = {
                    area = it
                },
                label = {

                },
                placeholder = {

                }
            )


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
                    .padding(start = 29.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .height(55.dp)
                    .width(310.dp)
                    .background(color = Color(0xFFCDCBCB))
                    .align(Alignment.CenterHorizontally),
                value = data,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Data")
                },
                onValueChange = {
                    data = it
                },
                label = {

                },
                placeholder = {

                })

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
                    .padding(start = 29.dp)

            )
            OutlinedTextField(
                modifier = Modifier
                    .height(55.dp)
                    .clip(RoundedCornerShape(7.dp))
                    .width(310.dp)
                    .background(color = Color(0xFFCDCBCB))
                    .align(Alignment.CenterHorizontally),
                value = hora,
                onValueChange = {
                    hora = it
                },
                label = {
                    Text("")
                },
                placeholder = {
                    Text(text = "Digite o seu peso")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccessTime, contentDescription = "Data")
                },
            )
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
