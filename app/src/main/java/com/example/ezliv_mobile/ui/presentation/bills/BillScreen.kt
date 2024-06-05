package com.example.ezliv_mobile.ui.presentation.bills

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezliv_mobile.ui.presentation.auth.EmailTextField
import com.example.ezliv_mobile.ui.presentation.auth.LoginButton
import com.example.ezliv_mobile.ui.presentation.auth.PasswordTextField
import com.example.ezliv_mobile.ui.presentation.home.Cabecalho

class BillScreen {
    @Composable
    fun BillScreenView() {
        var email by remember { mutableStateOf(value = "") }
        var senha by remember { mutableStateOf(value = "") }
        var instalacao by remember { mutableStateOf(value = "") }
        val fullName : String = ""
        Scaffold (topBar = {
            Cabecalho(userName = fullName, onClickExit = {})
        }){
            Box (modifier = Modifier.padding(it)){
               Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
                   Spacer(modifier = Modifier.height(24.dp))
                   EmailTextField(email, onValueChange = { email = it })
                   Spacer(modifier = Modifier.height(6.dp))
                   PasswordTextField(senha, onValueChange = { senha = it })
                   EzLivTextField(label = "Código de instalação", value = instalacao, onValueChange = {
                          instalacao = it
                   })
                   Spacer(modifier = Modifier.height(12.dp))
                   LoginButton(onClick = {

                   })
               }
            }
        }
    }

    @Composable
    fun EzLivTextField(label:String, value: String, onValueChange: (String) -> Unit) {
        TextField(
            value = value,
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Build,
                    contentDescription = "passwordLock",
                )
            },
            onValueChange = { it -> onValueChange(it) },
            label = { Text(text = label) },
            placeholder = { Text(text = label) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp))
                .width(340.dp)
                .height(100.dp)
                .padding(bottom = 40.dp)
        )
    }

    @Preview
    @Composable
    fun PreviewBillScreen() {
        BillScreenView()
    }
}