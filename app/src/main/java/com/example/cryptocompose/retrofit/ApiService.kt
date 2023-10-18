package com.example.cryptocompose.retrofit

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("/api/v3/coins/markets?vs_currency=usd&ids=bitcoin&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en")
    suspend fun getCrypto(): Response<List<CryptoPrices>>

    companion object{
        var apiService: ApiService? = null

        fun getInstance(): ApiService {
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.coingecko.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}
