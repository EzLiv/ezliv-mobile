package com.example.ezliv_mobile.ui.presentation.apartamento.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DeleteUserDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Confirmar Exclusão") },
            text = { Text("Tem certeza que deseja excluir este visitante?") },
            confirmButton = {
                Button(
                    onClick = { onConfirm() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF012A4A))
                ) {
                    Text("Confirmar", color = Color.White)
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFF012A4A))
                ) {
                    Text("Cancelar", color = Color(0xFF012A4A))
                }
            }
        )
    }
}