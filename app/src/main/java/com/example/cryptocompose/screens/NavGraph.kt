package com.example.cryptocompose.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptocompose.MainViewModel
import com.example.cryptocompose.retrofit.CryptoPrices
import com.example.cryptocompose.retrofit.CryptoViewModel

@Composable
fun NavGraph(
    navController: NavHostController
){
            NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route
        ){
            CryptoList(navController)
        }
        composable(
            route = Screen.BTC.route
        ){
            BitcoinPrice(
                mainViewModel = MainViewModel(),
                navController
            )
        }
        composable(
            route = Screen.ETH.route
        ){
            EthereumPrice(navController)
        }
    }
}