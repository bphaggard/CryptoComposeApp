package com.example.cryptocompose.screencomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import coil.compose.ImagePainter.State.Empty.painter
import com.example.cryptocompose.ui.theme.oswaldFamily

@Composable
fun CryptoCard(
    title: String,
    image: Int,
    modifier: Modifier,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(0.9f)
            .clickable{ onClick() }
            .then(modifier),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(0.8f),
                painter = painterResource(id = image),
                contentDescription = "crypto logo")
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = title,
                fontSize = 22.sp,
                fontFamily = oswaldFamily,
                fontWeight = FontWeight.Light)
        }
    }
}