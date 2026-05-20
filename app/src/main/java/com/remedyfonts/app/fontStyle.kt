package com.remedyfonts.app

data class FontStyle(
    val name: String,
    val isPremium: Boolean,
    val transform: (String) -> String
)
