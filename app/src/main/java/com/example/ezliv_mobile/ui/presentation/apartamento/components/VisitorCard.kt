package com.example.ezliv_mobile.ui.presentation.apartamento.components

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezliv_mobile.ui.domain.apartment.models.Resident
import com.example.ezliv_mobile.ui.domain.apartment.models.VisitorModel
import com.example.ezliv_mobile.ui.presentation.apartamento.formatCpf


@Composable
fun VisitorCard(onClick: () -> Unit, visitor: VisitorModel) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick)
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
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "Resident Icon"
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = visitor.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = formatCpf(visitor.cpf),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = visitor.email,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Text(
                            text = "Liberado de:",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.W500
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = visitor.releaseDate,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                    Row {
                        Text(
                            text = "Até:",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.W500
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = visitor.untilDate,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewVisitorCard() {
    VisitorCard(
        onClick = {},
        visitor = VisitorModel(
            id = "1",
            name = "João",
            cpf = "12345678900",
            email = "lusilvamartines@gmail.com",
            apartmentId = "2131241244",
            releaseDate = "05/03/2024 09:00",
            untilDate = "05/07/2024 12:00"
        )
    )
}