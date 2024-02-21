package com.example.bagu.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recommendation(
    @StringRes val name: Int,
    @StringRes val information: Int,
    @StringRes val address: Int,
    @StringRes val contactNumber: Int? = null,
    @DrawableRes val images: List<Int>
)
