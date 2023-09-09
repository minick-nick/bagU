package com.example.bagu.data

import com.example.bagu.R
import com.example.bagu.model.Category


object LocalCategoriesDataProvider {
    val allCategories = listOf(
        Category(
            name = R.string.coffee_shops,
            image = R.drawable.coffee_cup_coffee_svgrepo_com
        ),
        Category(
            name = R.string.restaurants,
            image = R.drawable.dinner_lunch_svgrepo_com
        ),
        Category(
            name = R.string.parks,
            image = R.drawable.park_svgrepo_com
        ),
        Category(
            name = R.string.shopping_centers,
            image = R.drawable.shop_shopping_store_svgrepo_com
        ),
    )
}