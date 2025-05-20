package com.example.bobabliss

data class BobaItem(
    val imageResId: Int,
    val title: String,
    val flavor: String,
    val description: String = "This is a refreshing and flavorful boba tea loved by many.",
    val volume: String = "160ml",
    val price: Double = 4.99
)
