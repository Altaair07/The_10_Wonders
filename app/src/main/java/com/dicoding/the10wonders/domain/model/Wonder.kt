package com.dicoding.the10wonders.domain.model

import androidx.annotation.DrawableRes

data class Wonder(
    val id: Int = 0,
    val title: String = "",
    @DrawableRes
    val imageRes: Int = 0,
    val description: String = "",
    @DrawableRes
    val wonderMap: Int? = null,
)
