package com.saveetha.ratemyprof

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme
import androidx.compose.material3.Scaffold

@Composable
fun InitialPage(
    onStudentClick: () -> Unit,
    onProfessorClick: () -> Unit,
    onAdminClick: () -> Unit, modifier: Modifier = Modifier, onRegisterCollegeClick: () -> Unit
) {

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

        Spacer(modifier = Modifier.height(65.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            RoleCard(R.drawable.student_ic, "Student") { onStudentClick() }
            Spacer(modifier = Modifier.height(100.dp))
            RoleCard(R.drawable.dean_ic, "Professor") { onProfessorClick() }
        }

        Spacer(modifier = Modifier.height(40.dp))

        RoleCard(R.drawable.admin_ic, "Admin") { onAdminClick() }

        Spacer(modifier = Modifier.height(50.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            Text(
                text = "--------------------X--------------------",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "\uD83C\uDFE2 Register your college ?",
            color = Color(0xFF4CAF50),
            fontSize = 20.sp,
            modifier = Modifier.clickable { onRegisterCollegeClick()},
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Gray,
                    offset = Offset(2f, 2f),
                    blurRadius = 4f
                )
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun InitialPagePreview() {
    RateMyProfTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            InitialPage(
                onStudentClick = {},
                onProfessorClick = {},
                onAdminClick = {},
                onRegisterCollegeClick = {},
                modifier = Modifier.padding(innerPadding)
            )

        }
    }
}