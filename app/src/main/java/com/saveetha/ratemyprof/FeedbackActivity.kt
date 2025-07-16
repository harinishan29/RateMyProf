package com.saveetha.ratemyprof

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.saveetha.ratemyprof.api.RatingResponse
import com.saveetha.ratemyprof.api.RetrofitClient
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedbackActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Extract rating data from intent
        val profId = intent.getStringExtra("profId") ?: ""
        val teachingStyle = intent.getIntExtra("teachingStyle", 0)
        val encouraging = intent.getIntExtra("encouraging", 0)
        val useOfTechnology = intent.getIntExtra("useOfTechnology", 0)
        val respectForStudents = intent.getIntExtra("respectForStudents", 0)

        val teachingStyleOption = intent.getStringExtra("teachingStyleOption") ?: ""
        val encouragingOption = intent.getStringExtra("encouragingOption") ?: ""
        val useOfTechnologyOption = intent.getStringExtra("useOfTechnologyOption") ?: ""
        val respectForStudentsOption = intent.getStringExtra("respectForStudentsOption") ?: ""

        val regNo = intent.getStringExtra("regNo") ?: ""
        val university = intent.getStringExtra("university") ?: ""

        setContent {
            RateMyProfTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FeedbackUI(
                        navController = rememberNavController(),
                        profId = profId,
                        teachingStyle,
                        encouraging,
                        useOfTechnology,
                        respectForStudents,
                        teachingStyleOption,
                        encouragingOption,
                        useOfTechnologyOption,
                        respectForStudentsOption,
                        regNo,
                        university
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackUI(
    navController: NavHostController,
    profId: String,
    teachingStyle: Int,
    encouraging: Int,
    useOfTechnology: Int,
    respectForStudents: Int,
    teachingStyleOption: String,
    encouragingOption: String,
    useOfTechnologyOption: String,
    respectForStudentsOption: String,
    regNo: String,
    university: String
) {
    var feedbackText by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "RateMyProf.",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF7CA153)
                )
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                Image(
                    painter = painterResource(id = R.drawable.prof1),
                    contentDescription = "Professor Photo",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Mrs. Phoebe",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = "Assistant Professor",
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "Your Feedback",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                OutlinedTextField(
                    value = feedbackText,
                    onValueChange = { feedbackText = it },
                    placeholder = {
                        Text(text = "Enter your feedback..", color = Color.Black)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(150.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(color = Color.Black, textAlign = TextAlign.Start),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF7CA153),
                        unfocusedBorderColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        // ✅ Make API call here
                        RetrofitClient.instance.submitProfessorRating(
                            profID = profId,
                            teachingStyle = teachingStyle,
                            encouraging = encouraging,
                            useOfTechnology = useOfTechnology,
                            respectForStudents = respectForStudents,
                            teachingStyleOption = teachingStyleOption,
                            encouragingOption = encouragingOption,
                            useOfTechnologyOption = useOfTechnologyOption,
                            respectForStudentsOption = respectForStudentsOption,
                            regNo = regNo,
                            university = university,
                            feedback = feedbackText
                        ).enqueue(object : Callback<RatingResponse> {
                            override fun onResponse(call: Call<RatingResponse>, response: Response<RatingResponse>) {
                                if (response.isSuccessful && response.body()?.Status == true) {
                                    context.startActivity(Intent(context, FeedbackPostedActivity::class.java))
                                } else {
                                    Toast.makeText(context, response.body()?.Message ?: "Submission failed", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<RatingResponse>, t: Throwable) {
                                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7CA153)),
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Submit", color = Color.White)
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FeedbackUIPreview() {
    RateMyProfTheme {
        FeedbackUI(
            navController = rememberNavController(),
            profId = "123",
            teachingStyle = 4,
            encouraging = 5,
            useOfTechnology = 3,
            respectForStudents = 5,
            teachingStyleOption = "Clear explanations",
            encouragingOption = "Very Supportive",
            useOfTechnologyOption = "Uses slides",
            respectForStudentsOption = "Very respectful",
            regNo = "2021CSE001",
            university = "SCLAS"
        )
    }
}
