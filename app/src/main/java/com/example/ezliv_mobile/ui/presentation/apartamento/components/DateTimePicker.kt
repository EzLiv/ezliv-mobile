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
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toFormattedString(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    return this.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalDateTime(): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    return LocalDateTime.parse(this, formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toApiFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return this.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toFormattedString(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return this.format(formatter)
}
@RequiresApi(Build.VERSION_CODES.O)
fun LocalTime.toFormattedString(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return this.format(formatter)
}
@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalTime(): LocalTime? {
    return try {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        LocalTime.parse(this, formatter)
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        null
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toApiFormat(): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        this.format(formatter)
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        ""
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalTime.toApiFormat(): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        this.format(formatter)
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        ""
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomizedDateTimePicker(
    initialDateTime: LocalDateTime,
    onDateTimeSelected: (LocalDateTime) -> Unit,
    editing: Boolean,
    changeEditing: (Boolean) -> Unit,
    onCancel : () -> Unit,
    label: String
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

        Box(modifier = Modifier.padding(horizontal = 16.dp)){
            Text(
                text = label,
                color = if (editing) Color.Black else Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
        }

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = selectedDateTime.toFormattedString(),
                color = if (editing) Color.Black else Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Editar",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable {
                        datePickerDialog.show()
                        changeEditing(!editing)
                    }
                    .align(Alignment.CenterVertically)
            )
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(if (editing) Color.Black else Color.Gray)
        )
    }
}