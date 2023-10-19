package com.example.cryptocompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.math.RoundingMode
import java.net.URL

class MainViewModel : ViewModel() {

    var currentBtc by mutableStateOf("")
    var currentEth by mutableStateOf("")
    var btcValue by mutableStateOf("")
    var ethValue by mutableStateOf("")
    var eurValue by mutableStateOf("")
    var czkValue by mutableStateOf("")

    var userBtcInput by mutableStateOf("")
    var userEthInput by mutableStateOf("")

    fun computeCurrentBtcValues(){
        if (userBtcInput.isEmpty()){
            currentBtc = ""
            btcValue = ""
            eurValue = ""
            czkValue = ""
        } else {
            runBlocking {
                val btc_value = async { findBtcValue() }
                val eur_value = async { findEurValue() }
                val czk_value = async { findCzkValue() }
                val cryptoAmount = userBtcInput.toDouble()
                val btcCurrent = btc_value.await().toBigDecimal().setScale(2, RoundingMode.UP)
                val usdCurrent = "${ btc_value.await() * cryptoAmount }".toDouble()
                val eurCurrent = usdCurrent * eur_value.await()
                val czkCurrent = usdCurrent * czk_value.await()
                currentBtc = btcCurrent.toString()
                btcValue = usdCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
                eurValue = eurCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
                czkValue = czkCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
            }
        }
    }

    fun computeCurrentEthValues(){
        if (userEthInput.isEmpty()){
            currentEth = ""
            ethValue = ""
            eurValue = ""
            czkValue = ""
        } else {
            runBlocking {
                val eth_value = async { findEthValue() }
                val eur_value = async { findEurValue() }
                val czk_value = async { findCzkValue() }
                val cryptoAmount = userEthInput.toDouble()
                val ethCurrent = eth_value.await().toBigDecimal().setScale(2, RoundingMode.UP)
                val usdCurrent = "${ eth_value.await() * cryptoAmount }".toDouble()
                val eurCurrent = usdCurrent * eur_value.await()
                val czkCurrent = usdCurrent * czk_value.await()
                currentEth = ethCurrent.toString()
                ethValue = usdCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
                eurValue = eurCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
                czkValue = czkCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
            }
        }
    }

    suspend fun findBtcValue(): Double { // stáhne z API hodnotu BTC v USD
        return withContext(Dispatchers.IO) {
            val response = URL("https://api.coindesk.com/v1/bpi/currentprice.json").readText()
            val json = JSONObject(response)
            val bpi = json.getJSONObject("bpi")
            val usdValue = bpi.getJSONObject("USD").getString("rate").getAmount().toDouble()
            usdValue
        }
    }

    suspend fun findEthValue(): Double {
        return withContext(Dispatchers.IO){
            val response = URL("https://api.coingecko.com/api/v3/simple/price?ids=ethereum&vs_currencies=usd").readText()
            val json = JSONObject(response)
            val eth = json.getJSONObject("ethereum")
            val ethUsValue = eth.toString().getAmount().toDouble()
            ethUsValue
        }
    }

    suspend fun findCzkValue(): Double { // stáhne z API hodnotu CZK vůči USD
        return withContext(Dispatchers.IO) {
            val response = URL("https://api.exchangerate-api.com/v4/latest/USD").readText()
            val json = JSONObject(response)
            val czkValue = json.getJSONObject("rates").getDouble("CZK")
            czkValue
        }
    }

    suspend fun findEurValue(): Double { // stáhne z API hodnotu EUR vůči USD
        return withContext(Dispatchers.IO) {
            val response = URL("https://api.exchangerate-api.com/v4/latest/USD").readText()
            val json = JSONObject(response)
            val eurValue = json.getJSONObject("rates").getDouble("EUR")
            eurValue
        }
    }

    fun String.getAmount(): String {
        return substring(indexOfFirst { it.isDigit() }, indexOfLast { it.isDigit() } + 1)
        .filter { it.isDigit() || it == '.' }
    }
}