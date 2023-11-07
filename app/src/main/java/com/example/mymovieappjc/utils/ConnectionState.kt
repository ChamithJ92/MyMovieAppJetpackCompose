package com.example.mymovieappjc.utils

sealed class ConnectionState{
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}
