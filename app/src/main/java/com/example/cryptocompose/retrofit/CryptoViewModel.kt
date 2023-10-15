package com.example.cryptocompose.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CryptoViewModel: ViewModel() {
    var cryptoListResponse: List<CryptoPrices> by mutableStateOf(listOf())
    var cryptoCurrent: CryptoPrices by mutableStateOf(CryptoPrices(0.0))
    var errorMessage: String by mutableStateOf("")

    fun getCryptoList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val response = apiService.getCrypto() // Make the network request

                if (response.isSuccessful) {
                    val cryptoList = response.body() // Get the response body

                    if (cryptoList != null) {
                        // Update the list if the response is successful
                        cryptoCurrent = cryptoList
                    } else {
                        // Handle the case where the response body is null
                        errorMessage = "Response body is null"
                    }
                } else {
                    // Handle the case where the response is not successful (e.g., status code is not in the 200-299 range)
                    errorMessage = "Request was not successful. Status code: ${response.code()}"
                }
            } catch (e: Exception) {
                // Handle exceptions, such as network errors
                errorMessage = e.message.toString()
            }
        }
    }
}
