package com.example.cryptocompose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.cryptocompose.R
import kotlinx.coroutines.delay

@Composable
fun LottieAnimation(
    navController : NavController
){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie))
    
    LaunchedEffect(key1 = true){
        delay(3000L)
        navController.navigate(Screen.Home.route)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        com.airbnb.lottie.compose.LottieAnimation(
            modifier = Modifier.size(300.dp),
            composition = composition
        )
    }
}