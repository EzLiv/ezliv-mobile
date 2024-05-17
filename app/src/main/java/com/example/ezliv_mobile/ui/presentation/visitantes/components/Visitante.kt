package com.example.ezliv_mobile.ui.presentation.visitantes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezliv_mobile.R

@Composable
fun Visitante(){
    Card (
        modifier = Modifier
            .height(111.dp)
            .width(331.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = null, modifier = Modifier.size(90.dp))
            Column {
                Text(text = "Luís Jardim")
                Text(text = "478.209.998-35")
                Text(text = "luis.jardim@gmail.com")
            }
            Icon(
                modifier = Modifier.padding(0.dp),
                imageVector = Icons.Default.Edit,
                contentDescription = "edit"

            )
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "delete"

            )
        }
    }
}

@Preview
@Composable
fun VisitantePreview() {
    Visitante()
}