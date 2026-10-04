package com.example.lafyuu_projectfinal.screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lafyuu_projectfinal.ui.theme.Blue63
import com.example.lafyuu_projectfinal.ui.theme.BlueFF
import com.example.lafyuu_projectfinal.ui.theme.GrayB1
import com.example.lafyuu_projectfinal.ui.theme.GreyFF
import com.example.lafyuu_projectfinal.ui.theme.RedFB

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(5.dp),
        placeholder = { Text(placeholder, fontSize = 12.sp, color = GrayB1) },
        leadingIcon = { Icon(painterResource(icon), contentDescription = null) },
        isError = isError,
        visualTransformation = if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            errorBorderColor = RedFB,
            errorLeadingIconColor = RedFB,
            errorTextColor = Blue63,
            errorCursorColor = RedFB,
            focusedBorderColor = BlueFF,
            unfocusedBorderColor = GreyFF,
            focusedLeadingIconColor = BlueFF,
            unfocusedLeadingIconColor = GrayB1,
            focusedTextColor = Blue63,
            unfocusedTextColor = Blue63,
            cursorColor = BlueFF
        )
    )
}