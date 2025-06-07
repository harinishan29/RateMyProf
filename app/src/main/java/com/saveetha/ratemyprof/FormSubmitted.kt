package com.saveetha.ratemyprof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.saveetha.ratemyprof.R
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme

class FormSubmittedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateMyProfTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    FormSubmittedScreen(onGoHomeClicked = {
                        // TODO: Navigate back to home
                    })
                }
            }
        }
    }
}

@Composable
fun FormSubmittedScreen(onGoHomeClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "RateMyProf.",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Replace this with your actual icon if available
        Icon(
            painter = painterResource(id = R.drawable.mailbox), // Your vector/PNG icon
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 24.dp),
            tint = Color(0xFF4CAF50)
        )

        Text(
            text = "Thank you",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your message has been received.\nOne of our team members will be\nin touch with you shortly.",
            fontSize = 16.sp,
            color = Color.Gray,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 8.dp),
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onGoHomeClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A))
        ) {
            Text(text = "Go Home", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormSubmittedScreenPreview() {
    RateMyProfTheme {
        FormSubmittedScreen(onGoHomeClicked = {})
    }
}

