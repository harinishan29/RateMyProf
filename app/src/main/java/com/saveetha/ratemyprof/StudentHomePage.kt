package com.saveetha.ratemyprof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material. icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme


class StudentHomePage : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentDashboardScreenNav(onClickStartRate = {})
        }
    }
}


@Composable
fun StudentDashboardScreen(
    studentName: String,
    professorsCount: Int,
    studentsCount: Int,
    reviewsCount: Int,
    onClickStartRate: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)
            Text(
                text = "RateMyProf.",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.Black)
        }

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Welcome Back,\n$studentName !!!",
            fontSize = 40.sp,
            color = Color.Black,
            fontFamily = FontFamily.Default, // Replace with RedRoseFamily if defined
            lineHeight = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        // Green Rectangle Container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFDDE8CC), RoundedCornerShape(16.dp))
                .padding(bottom = 30.dp)
                .heightIn(min = 400.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Info Cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatCard(
                    title = "No. of\nProfs.",
                    count = professorsCount,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "No. of\nStudents.",
                    count = studentsCount,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "No. of\nReviews.",
                    count = reviewsCount,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Rate, Review, and\nDiscover professors,\nto achieve academic\nexcellence.",
                fontSize = 30.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 35.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onClickStartRate() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A))
            ) {
                Text("Start Rating", fontSize = 25.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun StatCard(title: String, count: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(bottom = 8.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4CAF50),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Text(
            text = count.toString(),
            fontSize = 40.sp,
            color = Color.Black
        )
    }
}

@Composable
fun StudentDashboardScreenNav(onClickStartRate: () -> Unit) {
    RateMyProfTheme {
        StudentDashboardScreen(
            studentName = "Rachel",
            professorsCount = 120,
            studentsCount = 350,
            reviewsCount = 210,
            onClickStartRate = onClickStartRate
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudentDashboardPreview() {
    RateMyProfTheme {

        StudentDashboardScreen(
            studentName = "Rachel",
            professorsCount = 120,
            studentsCount = 350,
            reviewsCount = 210,
            onClickStartRate = {}
        )
    }
}
