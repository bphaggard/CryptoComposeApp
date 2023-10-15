package com.example.cryptocompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cryptocompose.MainViewModel
import com.example.cryptocompose.R
import com.example.cryptocompose.retrofit.CryptoItem
import com.example.cryptocompose.retrofit.CryptoPrices
import com.example.cryptocompose.retrofit.CryptoViewModel
import com.example.cryptocompose.ui.theme.btc_color
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun BitcoinPrice(mainViewModel: MainViewModel,
                 navController: NavController,
                 cryptoViewModel: CryptoViewModel){

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
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
                            text = "${cryptoViewModel.getCryptoList()}",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(20.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "enter your BTC amount") }
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Button(
                    onClick = {
                        coroutineScope.launch { mainViewModel.btcPrice() }
                        coroutineScope.launch { mainViewModel.czkPrice() }
                        coroutineScope.launch { mainViewModel.eurPrice() }
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
                Card(
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                        .fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(30.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(id = R.drawable.united_states),
                                contentDescription = "USA_logo"
                            )
                            Spacer(modifier = Modifier.padding(15.dp))
                            Text(
                                text = "BTC value in USD :",
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.padding(15.dp))
                            Text(text = mainViewModel.btcValue)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(id = R.drawable.european_union),
                                contentDescription = "EUR_logo"
                            )
                            Spacer(modifier = Modifier.padding(15.dp))
                            Text(
                                text = "BTC value in EUR :",
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.padding(15.dp))
                            Text(text = mainViewModel.eurValue)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(id = R.drawable.czech_republic),
                                contentDescription = "CZK_logo"
                            )
                            Spacer(modifier = Modifier.padding(15.dp))
                            Text(
                                text = "BTC value in CZK :",
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.padding(15.dp))
                            Text(text = mainViewModel.czkValue)
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(20.dp))
            }
        })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview(){
    BitcoinPrice(mainViewModel = MainViewModel(),
        navController = rememberNavController(),
        cryptoViewModel = CryptoViewModel()
    )
}