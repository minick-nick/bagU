package com.example.bagu.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.bagu.R
import com.example.bagu.data.Category
import com.example.bagu.model.Recommendation
import com.example.bagu.ui.utils.ScreensForListAndDetailContentType
import com.example.bagu.ui.utils.ScreensForListOnlyContentType
import com.example.bagu.uitls.ContentType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BagUViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun selectCategory(category: Category) {
        _uiState.update {
            it.copy(
                selectedCategory = category
            )
        }
    }

    fun updateListOnlyScreen(screen: ScreensForListOnlyContentType) {
        var temp: ScreensForListAndDetailContentType
        @StringRes var listAndDetailCurrentAppBarTitle = R.string.app_name
        @StringRes var listOnlyCurrentAppBarTitle: Int? = null

        when (screen) {
            ScreensForListOnlyContentType.Home -> {
                listOnlyCurrentAppBarTitle = R.string.app_name
                temp = ScreensForListAndDetailContentType.CategoriesAndRecommendations
            }
            ScreensForListOnlyContentType.Recommendations -> {
                listOnlyCurrentAppBarTitle = uiState.value.selectedCategory.name
                temp = ScreensForListAndDetailContentType.CategoriesAndRecommendations
            }
            else -> {
                temp = ScreensForListAndDetailContentType.RecommendationsAndDetails
                listAndDetailCurrentAppBarTitle = uiState.value.selectedCategory.name
            }
        }
        _uiState.update {
            it.copy(
                currentListOnlyScreen = screen,
                currentListAndDetailScreen = temp,
                listOnlyCurrentAppBarTitle = listOnlyCurrentAppBarTitle,
                listAndDetailCurrentAppBarTitle = listAndDetailCurrentAppBarTitle
            )
        }
    }

    fun updateListAndDetailScreen(screen: ScreensForListAndDetailContentType) {
        var temp: ScreensForListOnlyContentType
        @StringRes var listAndDetailCurrentAppBarTitle = R.string.app_name

        when (screen) {
            ScreensForListAndDetailContentType.CategoriesAndRecommendations -> {
                temp = ScreensForListOnlyContentType.Recommendations
            }
            else -> {
                temp = ScreensForListOnlyContentType.RecommendationDetails
                listAndDetailCurrentAppBarTitle = uiState.value.selectedCategory.name
            }
        }

        @StringRes var listOnlyCurrentAppBarTitle: Int? = when(temp) {
            ScreensForListOnlyContentType.Home -> R.string.app_name
            ScreensForListOnlyContentType.Recommendations -> uiState.value.selectedCategory.name
            else -> null
        }

        _uiState.update {
            it.copy(
                currentListAndDetailScreen = screen,
                currentListOnlyScreen = temp,
                listOnlyCurrentAppBarTitle = listOnlyCurrentAppBarTitle,
                listAndDetailCurrentAppBarTitle = listAndDetailCurrentAppBarTitle
            )
        }
    }

    fun selectRecommendation(recommendation: Recommendation) {
        _uiState.update {
            it.copy(
                selectedRecommendation = recommendation
            )
        }
    }

    fun navigateToPreviousScreen(contentType: ContentType) {
        // Reset the current recommendation image in recommendation details screen
        _uiState.update { it.copy(currentImgInRecommendation = 0) }

        if (contentType == ContentType.ListOnly) {
            if(uiState.value.currentListOnlyScreen ==
                ScreensForListOnlyContentType.Recommendations) {
                    updateListOnlyScreen(ScreensForListOnlyContentType.Home)
            } else if(uiState.value.currentListOnlyScreen ==
                ScreensForListOnlyContentType.RecommendationDetails) {
                    updateListOnlyScreen(ScreensForListOnlyContentType.Recommendations)
            }
        } else {
            if (uiState.value.currentListAndDetailScreen ==
                ScreensForListAndDetailContentType.RecommendationsAndDetails) {
                    updateListAndDetailScreen(
                        ScreensForListAndDetailContentType.CategoriesAndRecommendations
                    )
            }
        }
    }

    fun nextImgInRecommendation() {
        val totalImgInSelectedRecommendation =
            _uiState.value.selectedRecommendation.images.size
        val currentImage = _uiState.value.currentImgInRecommendation

        if (currentImage < totalImgInSelectedRecommendation - 1) {
            _uiState.update {
                it.copy(
                    currentImgInRecommendation = currentImage.inc()
                )
            }
        }
    }

    fun prevImgInRecommendation() {
        val currentImage = _uiState.value.currentImgInRecommendation

        if (currentImage > 0) {
            _uiState.update {
                it.copy(
                    currentImgInRecommendation = currentImage.dec()
                )
            }
        }
    }

    fun canNavigateBack(contentType: ContentType): Boolean {
        return when (contentType) {
            ContentType.ListOnly ->
                uiState.value.currentListOnlyScreen != ScreensForListOnlyContentType.Home
            else ->
                uiState.value.currentListAndDetailScreen != ScreensForListAndDetailContentType
                    .CategoriesAndRecommendations
        }
    }
}