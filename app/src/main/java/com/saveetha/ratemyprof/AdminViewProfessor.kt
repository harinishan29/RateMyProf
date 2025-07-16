package com.saveetha.ratemyprof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme

class AdminViewProfessor : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateMyProfTheme {
                AdminViewProfessorScreen(
                    prof = AdminProfessor(
                        fullName = "Dr. Lavanya Menon",
                        designation = "Assistant Professor",
                        dept = "Electronics and Communication",
                        college = "Saveetha Engineering College",
                        mail = "lavanya@saveetha.ac.in",
                        contact = "+91 98765 43210"
                    ),
                    onRatingsClicked = {},
                    onReviewsClicked = {},
                    onDeleteClicked = {}
                )
            }
        }
    }
}

data class AdminProfessor(
    val fullName: String,
    val designation: String,
    val dept: String,
    val college: String,
    val mail: String,
    val contact: String
)

@Composable
fun AdminViewProfessorScreen(
    prof: AdminProfessor,
    onRatingsClicked: () -> Unit,
    onReviewsClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Icon",
                tint = Color.Gray,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = prof.fullName, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            Text(text = prof.designation, fontSize = 18.sp, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(24.dp))

            InfoRowCard(label = "Department", value = prof.dept)
            InfoRowCard(label = "University", value = prof.college)
            InfoRowCard(label = "Email", value = prof.mail)
            InfoRowCard(label = "Phone", value = prof.contact)

            Spacer(modifier = Modifier.height(32.dp))

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = onRatingsClicked,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Icon(Icons.Default.Star, contentDescription = "Ratings", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("View Ratings", color = Color.White)
                }

                Button(
                    onClick = onReviewsClicked,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Icon(Icons.Default.Email, contentDescription = "Reviews", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("View Reviews", color = Color.White)
                }

                Button(
                    onClick = onDeleteClicked,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Delete Professor", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun InfoRowCard(label: String, value: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = label, fontSize = 14.sp, color = Color.Gray)
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewAdminViewProfessor() {
    RateMyProfTheme {
        AdminViewProfessorScreen(
            prof = AdminProfessor(
                fullName = "Dr. Lavanya Menon",
                designation = "Assistant Professor",
                dept = "Electronics and Communication",
                college = "Saveetha Engineering College",
                mail = "lavanya@saveetha.ac.in",
                contact = "+91 98765 43210"
            ),
            onRatingsClicked = {},
            onReviewsClicked = {},
            onDeleteClicked = {}
        )
    }
}
