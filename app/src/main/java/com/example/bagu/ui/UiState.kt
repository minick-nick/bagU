package com.example.bagu.ui

import androidx.annotation.StringRes
import com.example.bagu.R
import com.example.bagu.data.Category
import com.example.bagu.data.LocalDataProvider
import com.example.bagu.model.Recommendation
import com.example.bagu.ui.utils.ScreensForListAndDetailContentType
import com.example.bagu.ui.utils.ScreensForListOnlyContentType

data class UiState(
    val currentListOnlyScreen: ScreensForListOnlyContentType
        = ScreensForListOnlyContentType.Home,
    val currentListAndDetailScreen: ScreensForListAndDetailContentType
        = ScreensForListAndDetailContentType.CategoriesAndRecommendations,
    @StringRes val listOnlyCurrentAppBarTitle: Int? = R.string.app_name,
    @StringRes val listAndDetailCurrentAppBarTitle: Int = R.string.app_name,
    val selectedCategory: Category = LocalDataProvider.defaultCategory,
    val selectedRecommendation: Recommendation = LocalDataProvider.defaultRecommendation,
    val currentImgInRecommendation: Int = 0,
)
