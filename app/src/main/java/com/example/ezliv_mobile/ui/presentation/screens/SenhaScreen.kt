package com.example.ezliv_mobile.ui.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezliv_mobile.R
import com.example.ezliv_mobile.ui.presentation.theme.EzlivmobileTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EzlivmobileTheme {

            }
        }
    }
}

@Composable
fun RegisterPassword() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF012A4A))
    ) {
        LogoEzliv()
        NewPasswordTextField()
        Spacer(modifier = Modifier.height(4.dp))
        ConfirmPasswordTextField()
        Spacer(modifier = Modifier.height(12.dp))
        PasswordButton(onClick = { /*TODO*/ })
        Spacer(modifier = Modifier.height(30.dp))
        IconBottom()

    }
}

@Composable
fun LogoEzliv() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp, top = 120.dp, start = 22.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.ezliv),
            contentDescription = "Logo"
        )
    }

}
@Composable
fun NewPasswordTextField() {
    var novaSenha by remember { mutableStateOf(value = "") }

    TextField(
        value = novaSenha,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "emailIcon",
                tint = Color.White
            )
        },
        onValueChange = { novaSenha = it },
        label = { Text(text = "Nova Senha", color = Color.White) },
        placeholder = { Text(text = "Nova Senha") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .clip(RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp))
            .width(340.dp)
            .height(60.dp)
    )
}

@Composable
fun ConfirmPasswordTextField() {
    var senha by remember { mutableStateOf(value = "") }
    TextField(
        value = senha,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "passwordLock",
                tint = Color.White
            )
        },
        onValueChange = { senha = it },
        label = { Text(text = "Confirme a senha", color = Color.White) },
        placeholder = { Text(text = "Confirme a senha") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier
            .clip(RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp))
            .width(340.dp)
            .height(100.dp)
            .padding(bottom = 40.dp)
    )
}

@Composable
fun PasswordButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF001A2E)),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(300.dp)
            .height(55.dp)
    ) {
        Text(text = "Redefinir", fontSize = 20.sp)
    }
}

@Composable
fun IconBottom() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.building),
            contentDescription = "Building",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(210.dp)
                .fillMaxSize()
                .align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPasswordPreview() {
    EzlivmobileTheme {
        RegisterPassword()
    }
}
