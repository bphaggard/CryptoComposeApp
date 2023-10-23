package com.example.cryptocompose.network

interface ConnectivityObserver {

    fun observe(): kotlinx.coroutines.flow.Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}