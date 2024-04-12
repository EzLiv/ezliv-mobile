package com.example.ezliv_mobile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezliv_mobile.R

@Composable
fun EntregaItem() {
    Surface (modifier = Modifier
        .height(100.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(4.dp)),color = Color(0xFF012A4A))
        {
    Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)){
        Icon(painter = painterResource(id = R.drawable.encomenda), contentDescription = "Caixa", modifier = Modifier.
        size(70.dp), tint = Color.White)
        Column (modifier = Modifier.padding(8.dp)) {
            Text(text = "Objeto N°: 3212908231BR", color = Color.White)
            Text(text = "Data de Recebimento: 20/01/2024", color = Color.White)
            Text(text = "Status: Entregue",
                color =  Color.White)
        }
    }
    }
}

@Preview
@Composable
fun EntregaItemPreview() {
    EntregaItem()
}