package com.saveetha.ratemyprof

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saveetha.ratemyprof.api.LoginResponse
import com.saveetha.ratemyprof.api.RetrofitClient
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun StudentLoginScreen(onLoginSuccess: () -> Unit) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    var regNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }
    val prefs = remember { StudentPrefs(context) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "RateMyProf.",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Log in",
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF2E7D32)
        )

        Spacer(modifier = Modifier.height(100.dp))

        OutlinedTextField(
            value = regNumber,
            onValueChange = { regNumber = it },
            label = { Text("Registration number") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                focusManager.clearFocus()
                isLoading = true

                val call = RetrofitClient.instance.loginStudent(regNumber, password)
                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        isLoading = false
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse?.Status == true) {
                                Toast.makeText(context, "Welcome ${loginResponse.Data[0].FirstName}", Toast.LENGTH_SHORT).show()
                                val student = loginResponse.Data[0]
                                prefs.saveStudentData(
                                    student.StudID,
                                    student.FirstName,
                                    student.LastName,
                                    student.RegNo,
                                    student.University,
                                    student.Dept,
                                    student.Email,
                                    student.PhnNo,
                                    student.Username
                                )
                                onLoginSuccess()
                            } else {
                                Toast.makeText(context, loginResponse?.Message ?: "Login failed", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Server error!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        isLoading = false
                        Toast.makeText(context, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 12.dp
            ),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Logging in..." else "Log in", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudentLoginScreenPreview() {
    RateMyProfTheme {
        StudentLoginScreen {}
    }
}
