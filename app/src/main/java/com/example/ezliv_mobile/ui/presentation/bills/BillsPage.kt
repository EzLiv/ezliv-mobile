package com.example.ezliv_mobile.ui.presentation.bills

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.ezliv_mobile.ui.domain.bills.models.BillModel
import com.example.ezliv_mobile.ui.presentation.auth.EmailTextField
import com.example.ezliv_mobile.ui.presentation.auth.LoginButton
import com.example.ezliv_mobile.ui.presentation.auth.PasswordTextField
import com.example.ezliv_mobile.ui.presentation.auth.result.LoginResult
import com.example.ezliv_mobile.ui.presentation.auth.view_model.AuthViewModel
import com.example.ezliv_mobile.ui.presentation.bills.result.BillsResult
import com.example.ezliv_mobile.ui.presentation.bills.view_model.BillsViewModel
import com.example.ezliv_mobile.ui.presentation.ui.components.AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillsPage(navController: NavController, billsViewModel: BillsViewModel) {
    val result by billsViewModel.result.observeAsState()

    var email by remember {
        mutableStateOf(
            billsViewModel.preferencesManager.getData(
                "electricity_mail",
                ""
            )
        )
    }
    var senha by remember {
        mutableStateOf(
            billsViewModel.preferencesManager.getData(
                "electricity_password",
                ""
            )
        )
    }


    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF012A4A),
            ),
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Contas",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                    )
                }
            },
        )
    },
        bottomBar = {
            AppBar(navController)
        }) {
        Column(
            Modifier
                .padding(it)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (result) {
                is BillsResult.Success -> {
                    val bills = (result as BillsResult.Success).data
                    BillsBody(bills)
                }

                is BillsResult.Loading -> {
                    CircularProgressIndicator(
                        color = Color(0xFF012A4A),
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                    )
                }

                else -> {
                    EmailTextField(email, onValueChange = { email = it })
                    Spacer(modifier = Modifier.height(4.dp))
                    PasswordTextField(senha, onValueChange = { senha = it })
                    Spacer(modifier = Modifier.height(12.dp))
                    LoginButton(onClick = {
                        billsViewModel.getBills(email, senha)
                    })

                }
            }
        }
    }
}

@Composable
fun BillsBody(bills: List<BillModel>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(bills) { bill ->
            BillCard(bill)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun BillCard(bill: BillModel) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Valor: ${bill.amount}", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Status: ${bill.invoiceStatus}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Data de vencimento: ${bill.billDueDate}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Mês de referência: ${bill.monthReference}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            SelectionContainer {
                Text(
                    text = "Código de barras: ${bill.barCode}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    val clipboardManager =
                        context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("Código de barras", bill.barCode)
                    clipboardManager.setPrimaryClip(clip)
                    Toast.makeText(context, "Código de barras copiado!", Toast.LENGTH_SHORT).show()
                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF012A4A))) {
                    Text("Copiar código de barras")
                }

                Button(onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(bill.archiveUrl))
                    startActivity(context, intent, null)
                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF012A4A))) {
                    Text("Abrir PDF")
                }
            }
        }
    }
}