package com.example.cryptocompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cryptocompose.R
import com.example.cryptocompose.screencomponents.CryptoCard
import com.example.cryptocompose.ui.theme.bebasNeueFamily
import com.example.cryptocompose.ui.theme.dancingScriptFamily

@Composable
fun CryptoList(navController: NavController){

    val scrollState = rememberLazyListState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
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
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxHeight(0.5f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.blockchain),
                        contentDescription = "blockchain logo")
                    Spacer(modifier = Modifier.padding(5.dp))
                    Column {
                        Text(
                            text = "Crypto Converter",
                            fontFamily = bebasNeueFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 35.sp
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(
                            text = "convert your crypto assets",
                            fontFamily = dancingScriptFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.12f))
        Text(
            text = "choose your cryptocurrency",
            fontFamily = bebasNeueFamily,
            fontSize = 22.sp)
        Spacer(modifier = Modifier.fillMaxHeight(0.08f))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                CryptoCard(
                    title = "Bitcoin",
                    image = R.drawable.btc,
                    modifier = Modifier,
                    onClick = { navController.navigate(Screen.BTC.route) })
            }
            item {
                CryptoCard(
                    title = "Ethereum",
                    image = R.drawable.eth,
                    modifier = Modifier,
                    onClick = { navController.navigate(Screen.ETH.route) })
            }
            item {
                CryptoCard(
                    title = "Cardano",
                    image = R.drawable.ada,
                    modifier = Modifier,
                    onClick = { navController.navigate(Screen.ADA.route) })
            }
            item {
                CryptoCard(
                    title = "Litecoin",
                    image = R.drawable.ltc,
                    modifier = Modifier,
                    onClick = { navController.navigate(Screen.LTC.route) })
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CryptoPreview(){
    CryptoList(navController = rememberNavController())
}