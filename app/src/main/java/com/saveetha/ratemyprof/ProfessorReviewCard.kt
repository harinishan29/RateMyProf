package com.saveetha.ratemyprof

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saveetha.ratemyprof.R
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme

@Composable
fun ProfessorReviewCard(
    name: String,
    title: String,
    rating: Float,
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFD9EAD3))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                StarRating(rating)
            }
        }
    }
}

@Composable
fun StarRating(rating: Float) {
    Row {
        for (i in 1..5) {
            val icon = if (i <= rating) R.drawable.filledstar else R.drawable.unfilledstar
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Star",
                modifier = Modifier
                    .size(18.dp)
                    .padding(end = 2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfessorCardPreview() {
    RateMyProfTheme {
        ProfessorReviewCard(
            name = "Mrs. Phoebe",
            title = "Assistant professor",
            rating = 4f,
            imageRes = R.drawable.prof1
        )
    }
}


