package com.example.bagu.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.bagu.R
import com.example.bagu.model.Recommendation

sealed class Category(
    open val name: Int,
    open val image: Int,
    open val recommendations: List<Recommendation>
) {
    data class CoffeeShop(
        @StringRes override val name: Int = R.string.coffee_shops,
        @DrawableRes override val image: Int = R.drawable.coffee_cup_coffee_svgrepo_com,
        override val recommendations: List<Recommendation> = listOf()
    ): Category(name, image, recommendations)

    data class Restaurant(
        @StringRes override val name: Int = R.string.restaurants,
        @DrawableRes override val image: Int = R.drawable.dinner_lunch_svgrepo_com,
        override val recommendations: List<Recommendation> = listOf()
    ): Category(name, image, recommendations)

    data class Park(
        @StringRes override val name: Int = R.string.parks,
        @DrawableRes override val image: Int = R.drawable.park_svgrepo_com,
        override val recommendations: List<Recommendation> = listOf()
    ): Category(name, image, recommendations)

    data class ShoppingCenter(
        @StringRes override val name: Int = R.string.shopping_centers,
        @DrawableRes override val image: Int = R.drawable.shop_shopping_store_svgrepo_com,
        override val recommendations: List<Recommendation> = listOf()
    ): Category(name, image, recommendations)
}
