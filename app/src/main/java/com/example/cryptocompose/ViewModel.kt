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

class MainViewModel: ViewModel() {

    var currentBtc by mutableStateOf("")
    var currentEth by mutableStateOf("")
    var currentAda by mutableStateOf("")
    var currentLtc by mutableStateOf("")
    var btcValue by mutableStateOf("")
    var ethValue by mutableStateOf("")
    var adaValue by mutableStateOf("")
    var ltcValue by mutableStateOf("")
    var eurValue by mutableStateOf("")
    var czkValue by mutableStateOf("")

    var userBtcInput by mutableStateOf("")
    var userEthInput by mutableStateOf("")
    var userAdaInput by mutableStateOf("")
    var userLtcInput by mutableStateOf("")

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

    fun computeCurrentAdaValues(){
        if (userAdaInput.isEmpty()){
            currentAda = ""
            adaValue = ""
            eurValue = ""
            czkValue = ""
        } else {
            runBlocking {
                val ada_value = async { findAdaValue() }
                val eur_value = async { findEurValue() }
                val czk_value = async { findCzkValue() }
                val cryptoAmount = userAdaInput.toDouble()
                val adaCurrent = ada_value.await().toBigDecimal().setScale(2, RoundingMode.UP)
                val usdCurrent = "${ ada_value.await() * cryptoAmount }".toDouble()
                val eurCurrent = usdCurrent * eur_value.await()
                val czkCurrent = usdCurrent * czk_value.await()
                currentAda = adaCurrent.toString()
                adaValue = usdCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
                eurValue = eurCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
                czkValue = czkCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
            }
        }
    }

    fun computeCurrentLtcValues(){
        if (userLtcInput.isEmpty()){
            currentLtc = ""
            ltcValue = ""
            eurValue = ""
            czkValue = ""
        } else {
            runBlocking {
                val ltc_value = async { findLtcValue() }
                val eur_value = async { findEurValue() }
                val czk_value = async { findCzkValue() }
                val cryptoAmount = userLtcInput.toDouble()
                val ltcCurrent = ltc_value.await().toBigDecimal().setScale(2, RoundingMode.UP)
                val usdCurrent = "${ ltc_value.await() * cryptoAmount }".toDouble()
                val eurCurrent = usdCurrent * eur_value.await()
                val czkCurrent = usdCurrent * czk_value.await()
                currentLtc = ltcCurrent.toString()
                ltcValue = usdCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
                eurValue = eurCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
                czkValue = czkCurrent.toBigDecimal().setScale(2, RoundingMode.UP).toString()
            }
        }
    }

    private suspend fun findBtcValue(): Double { // stáhne z API hodnotu BTC v USD
        return withContext(Dispatchers.IO) {
            val response = URL("https://api.coindesk.com/v1/bpi/currentprice.json").readText()
            val json = JSONObject(response)
            val bpi = json.getJSONObject("bpi")
            val usdValue = bpi.getJSONObject("USD").getString("rate").getAmount().toDouble()
            usdValue
        }
    }

    private suspend fun findEthValue(): Double {
        return withContext(Dispatchers.IO){
            val response = URL("https://api.coingecko.com/api/v3/simple/price?ids=ethereum&vs_currencies=usd").readText()
            val json = JSONObject(response)
            val eth = json.getJSONObject("ethereum")
            val ethUsValue = eth.toString().getAmount().toDouble()
            ethUsValue
        }
    }

    private suspend fun findAdaValue(): Double {
        return withContext(Dispatchers.IO){
            val response = URL("https://api.coingecko.com/api/v3/simple/price?ids=cardano&vs_currencies=usd").readText()
            val json = JSONObject(response)
            val ada = json.getJSONObject("cardano")
            val adaUsValue = ada.toString().getAmount().toDouble()
            adaUsValue
        }
    }

    private suspend fun findLtcValue(): Double {
        return withContext(Dispatchers.IO){
            val response = URL("https://api.coingecko.com/api/v3/simple/price?ids=litecoin&vs_currencies=usd").readText()
            val json = JSONObject(response)
            val ltc = json.getJSONObject("litecoin")
            val ltcUsValue = ltc.toString().getAmount().toDouble()
            ltcUsValue
        }
    }

    private suspend fun findCzkValue(): Double { // stáhne z API hodnotu CZK vůči USD
        return withContext(Dispatchers.IO) {
            val response = URL("https://api.exchangerate-api.com/v4/latest/USD").readText()
            val json = JSONObject(response)
            val czkValue = json.getJSONObject("rates").getDouble("CZK")
            czkValue
        }
    }

    private suspend fun findEurValue(): Double { // stáhne z API hodnotu EUR vůči USD
        return withContext(Dispatchers.IO) {
            val response = URL("https://api.exchangerate-api.com/v4/latest/USD").readText()
            val json = JSONObject(response)
            val eurValue = json.getJSONObject("rates").getDouble("EUR")
            eurValue
        }
    }

    private fun String.getAmount(): String {
        return substring(indexOfFirst { it.isDigit() }, indexOfLast { it.isDigit() } + 1)
        .filter { it.isDigit() || it == '.' }
    }
}