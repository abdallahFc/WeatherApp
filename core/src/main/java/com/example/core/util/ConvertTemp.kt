package com.example.core.util

fun Double.toCelsius(): Int  {
    return ((this - 32) * 5 / 9).toInt()
}