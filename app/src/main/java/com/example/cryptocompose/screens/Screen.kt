package com.example.cryptocompose.screens

sealed class Screen(val route: String){
    object Home: Screen(route = "main_screen")
    object BTC: Screen(route = "bitcoin_screen")
    object ETH: Screen(route = "ethereum_screen")
    object ADA: Screen(route = "cardano_screen")
    object LTC: Screen(route = "litecoin_screen")
}
