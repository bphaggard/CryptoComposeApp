package com.example.cryptocompose.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cryptocompose.MainViewModel
import com.example.cryptocompose.R
import com.example.cryptocompose.screencomponents.CurrenciesCard
import com.example.cryptocompose.ui.theme.btc_color
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun BitcoinPrice(mainViewModel: MainViewModel,
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
                            Icons.Rounded.ArrowBack,
                            "backIcon",
                            modifier = Modifier.background(
                                shape = CircleShape,
                                color = btc_color))
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent)
            )
        }, content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
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
                            painter = painterResource(id = R.drawable.bitcoin_logo),
                            contentDescription = "btc_logo"
                        )
                        Text(
                            text = "current BTC price :"
                        )
                        Text(
                            text = mainViewModel.currentBtc.toString(),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(20.dp))
                OutlinedTextField(
                    value = mainViewModel.userBtcInput,
                    onValueChange = { mainViewModel.userBtcInput = it },
                    label = { Text(text = "enter your BTC amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Button(
                    onClick = {
                        if (mainViewModel.userBtcInput.isNotEmpty()){
                            mainViewModel.computeCurrentBtcValues()
                        } else {
                            Toast.makeText(context, "Enter some value", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(btc_color),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 5.dp
                    )
                )
                {
                    Text(text = "CONVERT")
                }
                Spacer(modifier = Modifier.padding(20.dp))
                CurrenciesCard(
                    usLabel = "BTC value in USD :",
                    euLabel = "BTC value in EUR :",
                    czLabel = "BTC value in CZK :",
                    usValue = mainViewModel.btcValue,
                    euValue = mainViewModel.eurValue,
                    czValue = mainViewModel.czkValue
                )
                Spacer(modifier = Modifier.padding(20.dp))
            }
        })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview(){
    BitcoinPrice(mainViewModel = MainViewModel(),
        navController = rememberNavController())
}