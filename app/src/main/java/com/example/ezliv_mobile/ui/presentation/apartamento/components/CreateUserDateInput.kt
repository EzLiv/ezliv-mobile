package com.example.ezliv_mobile.ui.presentation.apartamento.components

import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateDateInput(
    initialDateTime: LocalDateTime,
    onDateTimeSelected: (LocalDateTime) -> Unit,
    onCancel: () -> Unit,
    label: String,
    hasError : Boolean
) {
    var selectedDateTime by remember { mutableStateOf(initialDateTime) }

    val context = LocalContext.current

    fun showTimePickerDialog(newDateTime: LocalDateTime) {
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                val finalDateTime = newDateTime.withHour(hourOfDay).withMinute(minute)
                selectedDateTime = finalDateTime
                onDateTimeSelected(finalDateTime)
            },
            newDateTime.hour,
            newDateTime.minute,
            true
        ).apply {
            setOnCancelListener { onCancel() }
        }.show()
    }

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val newDateTime =
                selectedDateTime.withYear(year).withMonth(month + 1).withDayOfMonth(dayOfMonth)
            showTimePickerDialog(newDateTime)
        },
        selectedDateTime.year,
        selectedDateTime.monthValue - 1,
        selectedDateTime.dayOfMonth
    ).apply {
        setOnCancelListener {
            onCancel()
        }
    }

    Column {

        Box {
            Text(
                text = label,
                color = if(hasError) Color.Red else Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
        }

        Row {
            Text(
                text = selectedDateTime.toFormattedString(),
                color = if(hasError) Color.Red else Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Date",
                modifier = Modifier
                    .clickable {
                        datePickerDialog.show()
                    }
                    .align(Alignment.CenterVertically)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(if(hasError) Color.Red else Color.Black)
        )
    }
}

@Composable
fun CreateUserInput(
    label: String,
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    keyBoardType: KeyboardType = KeyboardType.Text,
) {
    Column{
        Text(
            text = label,
            color = if (isError) Color.Red else Color.Black,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyBoardType
                ),
                value = text,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    color = if (isError) Color.Red else Color.Black,
                    fontSize = 18.sp
                ),
                cursorBrush = SolidColor(Color.Black),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(if (isError) Color.Red else Color.Black)
        )
    }
}