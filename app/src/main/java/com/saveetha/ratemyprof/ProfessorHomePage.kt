package com.saveetha.ratemyprof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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

class ProfessorHomePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateMyProfTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    ProfessorDashboardScreen(
                        professorName = "Rachel",
                        profCount = 120,
                        studentCount = 350,
                        reviewCount = 210
                    )
                }
            }
        }
    }
}

@Composable
fun ProfessorDashboardScreen(
    professorName: String,
    profCount: Int,
    studentCount: Int,
    reviewCount: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            // Top Bar
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
                text = "Welcome Back,\n$professorName !!!",
                fontSize = 40.sp,
                color = Color.Black,
                fontFamily = FontFamily.Default,
                lineHeight = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFFDDE8CC), RoundedCornerShape(16.dp))
                    .padding(bottom = 30.dp),
                verticalArrangement = Arrangement.Top
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StatCard(title = "No. of\nProfs.", count = profCount, modifier = Modifier.weight(1f))
                    StatCard(title = "No. of\nStudents.", count = studentCount, modifier = Modifier.weight(1f))
                    StatCard(title = "No. of\nReviews.", count = reviewCount, modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* TODO: Navigate to View Ratings */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A))
                    ) {
                        Text("View Ratings", fontSize = 18.sp, color = Color.White)
                    }

                    Button(
                        onClick = { /* TODO: Navigate to View Reviews */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A))
                    ) {
                        Text("View Reviews", fontSize = 18.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

//@Composable
//fun StatCard(title: String, count: Int, modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier.padding(horizontal = 6.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = Modifier.height(30.dp))
//        Card(
//            shape = RoundedCornerShape(12.dp),
//            colors = CardDefaults.cardColors(containerColor = Color.White),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(130.dp)
//                .padding(bottom = 8.dp)
//        ) {
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(vertical = 16.dp)
//            ) {
//                Text(
//                    text = title,
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color(0xFF4CAF50),
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//        }
//
//        Text(
//            text = count.toString(),
//            fontSize = 40.sp,
//            color = Color.Black
//        )
//    }
//}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfessorDashboardPreview() {
    RateMyProfTheme {
        ProfessorDashboardScreen(
            professorName = "Rachel",
            profCount = 120,
            studentCount = 350,
            reviewCount = 210
        )
    }
}
