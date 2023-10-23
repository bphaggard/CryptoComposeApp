package com.example.cryptocompose.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptocompose.MainViewModel

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
                navController)
        }
        composable(
            route = Screen.ETH.route
        ){
            EthereumPrice(
                mainViewModel = MainViewModel(),
                navController)
        }
        composable(
            route = Screen.ADA.route
                ){
            CardanoPrice(
                mainViewModel = MainViewModel(),
                navController)
        }
        composable(
            route = Screen.LTC.route
        ){
            LitecoinPrice(
                mainViewModel = MainViewModel(),
                navController)
        }
    }
}