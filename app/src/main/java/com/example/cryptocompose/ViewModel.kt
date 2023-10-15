package com.example.cryptocompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class MainViewModel : ViewModel() {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    var btcValue by mutableStateOf("")
    var eurValue by mutableStateOf("")
    var czkValue by mutableStateOf("")

    fun btcPrice() {
        ioScope.launch {
            val response = URL("https://api.coindesk.com/v1/bpi/currentprice.json").readText()
            val json = JSONObject(response)
            val bpi = json.getJSONObject("bpi")
            val usValue = bpi.getJSONObject("USD").getString("rate").getAmount().toDouble()
            btcValue = "%.2f".format(usValue)
        }
    }

    fun czkPrice(){
        ioScope.launch {
            val response = URL("https://api.exchangerate-api.com/v4/latest/USD").readText()
            val json = JSONObject(response)
            val czValue = json.getJSONObject("rates").getDouble("CZK")
            czkValue = czValue.toString()
        }
    }

    fun eurPrice(){
        ioScope.launch {
            val response = URL("https://api.exchangerate-api.com/v4/latest/USD").readText()
            val json = JSONObject(response)
            val euValue = json.getJSONObject("rates").getDouble("EUR")
            eurValue = euValue.toString()
        }
    }

    fun String.getAmount(): String {
        return substring(indexOfFirst { it.isDigit() }, indexOfLast { it.isDigit() } + 1)
        .filter { it.isDigit() || it == '.' }
    }
}