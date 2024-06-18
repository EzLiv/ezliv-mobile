package com.example.ezliv_mobile.ui.presentation.reservas.components

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezliv_mobile.ui.domain.reserves.model.ReserveModel
import com.example.ezliv_mobile.ui.presentation.apartamento.formatCpf

@Composable
fun ReserveCard(
    reserveModel: ReserveModel,
    onDeleteClicked: () -> Unit // Função para lidar com a exclusão da reserva
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .height(80.dp)
                        .width(100.dp)
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Reserve Icon"
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = reserveModel.commonAreaName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row {
                        Text(
                            text = "Data da reserva:",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.W500
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = reserveModel.date,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Text(
                            text = "Horário de ínicio:",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.W500
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = reserveModel.startDateTime,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                    Row {
                        Text(
                            text = "Horário de fim:",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.W500
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = reserveModel.endDateTime,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onDeleteClicked() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Reserve",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ReserveCardPreview() {
    ReserveCard(
        ReserveModel(
            id = "1",
            startDateTime = "10:00",
            endDateTime = "12:00",
            date = "10/10/2021",
            commonAreaName = "Salão de festas"
        ),
        onDeleteClicked = {}
    )
}