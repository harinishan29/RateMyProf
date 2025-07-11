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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
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

class ProfessorMyProfile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateMyProfTheme {
                ProfessorMyProfileScreen(
                    professor = Professor(
                        name = "Dr. Rachel",
                        title = "Associate Professor",
                        department = "Computer Science",
                        university = "Saveetha University",
                        email = "rachel@saveetha.ac.in",
                        phone = "+91 98765 43210"
                    ),
                    onLogoutClick = { finish() },
                    onEditProfileClick = {  }
                )
            }
        }
    }
}

data class Professor(
    val name: String,
    val title: String,
    val department: String,
    val university: String,
    val email: String,
    val phone: String
)

@Composable
fun ProfessorMyProfileScreen(
    professor: Professor,
    onLogoutClick: () -> Unit,
    onEditProfileClick: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Icon
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Picture",
                tint = Color.Gray,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(professor.name, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            Text(professor.title, fontSize = 18.sp, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(24.dp))

            ProfileInfoCard(label = "Department", value = professor.department)
            ProfileInfoCard(label = "University", value = professor.university)
            ProfileInfoCard(label = "Email", value = professor.email)
            ProfileInfoCard(label = "Phone", value = professor.phone)

            Spacer(modifier = Modifier.height(32.dp))

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onEditProfileClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Edit Profile")
                }

                Button(
                    onClick = onLogoutClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                ) {
                    Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Logout")
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun ProfileInfoCard(label: String, value: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label, fontSize = 14.sp, color = Color.Gray)
            Text(value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfessorMyProfilePreview() {
    RateMyProfTheme {
        ProfessorMyProfileScreen(
            professor = Professor(
                name = "Dr. Rachel",
                title = "Associate Professor",
                department = "Computer Science",
                university = "Saveetha University",
                email = "rachel@saveetha.ac.in",
                phone = "+91 98765 43210"
            ),
            onLogoutClick = {},
            onEditProfileClick = {}
        )
    }
}
