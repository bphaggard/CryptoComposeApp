package com.example.cryptocompose.retrofit

data class CryptoPrices(
    val symbol: String,
    val image: String,
    val name: String,
    val current_price: Double
)