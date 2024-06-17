package com.example.ezliv_mobile.ui.presentation.ui.helpers

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 11) text.text.substring(0, 11) else text.text
        val filled = trimmed.padEnd(11, ' ')

        val annotatedString = AnnotatedString.Builder().apply {
            append("(")
            append(filled.substring(0, 2))
            append(") ")
            append(filled.substring(2, 7))
            append("-")
            append(filled.substring(7, 11))
        }.toAnnotatedString()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 2 -> offset + 1
                    offset <= 7 -> offset + 3
                    offset <= 11 -> offset + 4
                    else -> 15
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 1 -> offset - 1
                    offset <= 5 -> offset - 3
                    offset <= 10 -> offset - 4
                    else -> 11
                }
            }
        }

        return TransformedText(annotatedString, offsetMapping)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatarData(dataString: String): String {
    val formatoOriginal = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale("pt", "BR"))

    val data = LocalDate.parse(dataString, formatoOriginal)
    return data.format(formatoBrasileiro)
}