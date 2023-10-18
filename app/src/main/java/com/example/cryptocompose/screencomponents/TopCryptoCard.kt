package com.example.cryptocompose.screencomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopCryptoCard(
    image: Int,
    label: String,
    value: String
){
    Card(
        modifier = Modifier
            .fillMaxHeight(0.3f)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 25.dp),
        shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.23f),
                painter = painterResource(id = image),
                contentDescription = "crypto_logo"
            )
            Text(
                text = label
            )
            Text(
                text = value,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}