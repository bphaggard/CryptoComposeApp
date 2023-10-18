package com.example.cryptocompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cryptocompose.R
import com.example.cryptocompose.screencomponents.CurrenciesCard
import com.example.cryptocompose.screencomponents.TopCryptoCard
import com.example.cryptocompose.ui.theme.eth_color

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EthereumPrice(navController: NavController){

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            "backIcon",
                            modifier = Modifier.background(
                                shape = CircleShape,
                                color = eth_color
                            ))
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent)
            )
        }, content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TopCryptoCard(
                    image = R.drawable.eth_logo,
                    label = "current ETH price :",
                    value = "")
                Spacer(modifier = Modifier.padding(20.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {  },
                    label = { Text(text = "enter your ETH amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(eth_color),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 5.dp
                    )) {
                    Text(text = "CONVERT")
                }
                Spacer(modifier = Modifier.padding(20.dp))
                CurrenciesCard(
                    usLabel = "ETH value in USD :",
                    euLabel = "ETH value in EUR :",
                    czLabel = "ETH value in CZK :",
                    usValue = "",
                    euValue = "",
                    czValue = "")
                Spacer(modifier = Modifier.padding(20.dp))
            }
        })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EthPreview(){
    EthereumPrice(navController = rememberNavController())
}