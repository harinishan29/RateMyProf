// ReviewCard.kt
package com.saveetha.ratemyprof

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme

data class Review(
    val date: String,
    val studentRegNo: String,
    val content: String
)

@Composable
fun ReviewCard(review: Review) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = review.studentRegNo,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color(0xFF2E7D32)
            )
            Text(
                text = review.date,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = review.content,
                fontSize = 16.sp
            )
        }
    }
}


@Preview
@Composable
private fun ReviewCardPreview() {
    RateMyProfTheme {

        val review = Review("2025-jan-23","1287127792","Hi")
        ReviewCard(review)
    }
}