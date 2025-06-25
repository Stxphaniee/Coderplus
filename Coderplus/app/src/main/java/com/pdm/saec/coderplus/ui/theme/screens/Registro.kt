package com.pdm.saec.coderplus.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm.saec.coderplus.model.User

@Composable
fun Registro(
    currentUser: User,
    onSave: (User) -> Unit,
    onCancel: () -> Unit,
) {
    var name by remember { mutableStateOf(currentUser.name) }
    var age by remember { mutableStateOf(currentUser.age.toString()) }
    var country by remember { mutableStateOf(currentUser.country) }
    var email by remember { mutableStateOf(currentUser.email) }
    var password by remember { mutableStateOf(currentUser.password) }


    val dataFieldBackgroundColor = Color(0xFF333760)
    val dataFieldContentColor = Color.White

    val labelTextColor = Color(0xFF333760)
    val labelCardBackgroundColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFFFFF), Color(0xFF004482))
                )
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Registro",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF004482)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(32.dp))

        @Composable
        fun LabeledInputField(
            label: String,
            value: String,
            onValueChange: (String) -> Unit,
            keyboardType: KeyboardType = KeyboardType.Text,
            visualTransformation: VisualTransformation = VisualTransformation.None // Nuevo parámetro
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .wrapContentWidth(align = Alignment.Start)
                        .padding(bottom = 4.dp),
                    shape = RoundedCornerShape(percent = 50),
                    colors = CardDefaults.cardColors(containerColor = labelCardBackgroundColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = label,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = labelTextColor,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }

                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = dataFieldBackgroundColor,
                        unfocusedContainerColor = dataFieldBackgroundColor,
                        disabledContainerColor = dataFieldBackgroundColor,
                        errorContainerColor = dataFieldBackgroundColor,

                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent,

                        cursorColor = dataFieldContentColor,
                        focusedLabelColor = Color.Transparent,
                        unfocusedLabelColor = Color.Transparent,
                        focusedTextColor = dataFieldContentColor,
                        unfocusedTextColor = dataFieldContentColor
                    ),
                    shape = RoundedCornerShape(percent = 50),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                    singleLine = true,
                    visualTransformation = visualTransformation
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        LabeledInputField(
            label = "Nombre:",
            value = name,
            onValueChange = { name = it }
        )

        LabeledInputField(
            label = "Edad:",
            value = age,
            onValueChange = { newAge ->
                if (newAge.all { it.isDigit() } || newAge.isEmpty()) {
                    age = newAge
                }
            },
            keyboardType = KeyboardType.Number
        )

        LabeledInputField(
            label = "País:",
            value = country,
            onValueChange = { country = it }
        )

        LabeledInputField(
            label = "Correo:",
            value = email,
            onValueChange = { email = it },
            keyboardType = KeyboardType.Email
        )

        LabeledInputField(
            label = "Contraseña:",
            value = password,
            onValueChange = { password = it },
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val updatedUser = currentUser.copy(
                    name = name,
                    age = age,
                    country = country,
                    email = email,
                    password = password
                )
                onSave(updatedUser)
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor =  Color(0xFFFFFFFF) )
        ) {
            Text(
                text = "Aceptar",
                color = Color(0xFF333760),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}