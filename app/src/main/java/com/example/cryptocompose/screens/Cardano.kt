package com.example.cryptocompose.screens

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cryptocompose.MainViewModel
import com.example.cryptocompose.R
import com.example.cryptocompose.screencomponents.CurrenciesCard
import com.example.cryptocompose.screencomponents.TopCryptoCard
import com.example.cryptocompose.ui.theme.ada_color
import com.example.cryptocompose.ui.theme.eth_color

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CardanoPrice(
    mainViewModel: MainViewModel,
    navController: NavController
){
    val context = LocalContext.current

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
                                color = ada_color
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
                    image = R.drawable.cardano_logo,
                    label = "current ADA price :",
                    value = mainViewModel.currentAda)
                Spacer(modifier = Modifier.padding(20.dp))
                OutlinedTextField(
                    value = mainViewModel.userAdaInput,
                    onValueChange = { mainViewModel.userAdaInput = it },
                    label = { Text(text = "enter your ADA amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Button(
                    onClick = {
                        if (mainViewModel.userAdaInput.isNotEmpty()){
                            mainViewModel.computeCurrentAdaValues()
                        } else {
                            Toast.makeText(context, "Enter some value", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(ada_color),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 5.dp
                    )) {
                    Text(text = "CONVERT")
                }
                Spacer(modifier = Modifier.padding(20.dp))
                CurrenciesCard(
                    usLabel = "ADA value in USD :",
                    euLabel = "ADA value in EUR :",
                    czLabel = "ADA value in CZK :",
                    usValue = mainViewModel.adaValue,
                    euValue = mainViewModel.eurValue,
                    czValue = mainViewModel.czkValue)
                Spacer(modifier = Modifier.padding(20.dp))
            }
        })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdaPreview(){
    CardanoPrice(
        mainViewModel = MainViewModel(),
        navController = rememberNavController())
}