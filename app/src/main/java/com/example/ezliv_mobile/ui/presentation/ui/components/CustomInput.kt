package com.example.ezliv_mobile.ui.presentation.ui.components

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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ezliv_mobile.ui.presentation.ui.helpers.PhoneVisualTransformation

@Composable
fun CustomInput(
    label: String,
    isEditable: Boolean = false,
    text: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation?,
    onEdited : () -> Unit,
    onCancelEdit: () -> Unit,
    isError : Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = label,
            color = if (isError) Color.Red else if (isFocused) Color.Black else Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 4.dp, start = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (label == "Telefone") {
                        KeyboardType.Phone
                    } else {
                        KeyboardType.Text
                    }
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        softwareKeyboardController?.hide()
                        focusManager.clearFocus()
                        onEdited()
                    }
                ),
                value = text,
                onValueChange = onValueChange,
                textStyle = TextStyle(color = if (isError) Color.Red else if (isFocused) Color.Black else Color.Gray, fontSize = 18.sp),
                cursorBrush = SolidColor(Color.Black),
                visualTransformation = visualTransformation ?: VisualTransformation.None,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }
            )

            Spacer(modifier = Modifier.width(4.dp))
            if (isEditable && !isFocused) {
                Text(
                    text = "Editar",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable { focusRequester.requestFocus() }
                )
            }
            if (isEditable && isFocused) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = Color.Green,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                       if(!isError){
                           focusManager.clearFocus()
                           softwareKeyboardController?.hide()
                           onEdited()
                       }

                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "",
                    tint = Color.Red,
                    modifier = Modifier.clickable {
                        softwareKeyboardController?.hide()
                        focusManager.clearFocus()
                        onCancelEdit()
                    }
                )
            }

        }

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(if (isError) Color.Red else if (isFocused) Color.Black else Color.Gray)
        )
    }
}
