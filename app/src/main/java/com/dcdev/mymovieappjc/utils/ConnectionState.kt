package com.dcdev.mymovieappjc.utils

sealed class ConnectionState{
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}
