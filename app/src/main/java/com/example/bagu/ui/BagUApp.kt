package com.example.bagu.ui

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bagu.R
import com.example.bagu.data.LocalDataProvider
import com.example.bagu.ui.utils.ScreensForListAndDetailContentType
import com.example.bagu.ui.utils.ScreensForListOnlyContentType
import com.example.bagu.uitls.ContentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BagUApp(
    viewModel: BagUViewModel = viewModel(),
    windowSize: WindowWidthSizeClass
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentActivity = LocalContext.current as Activity

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> ContentType.ListOnly
        WindowWidthSizeClass.Expanded -> ContentType.ListAndDetail
        else -> ContentType.ListOnly
    }

    Scaffold(
        topBar = {
            BagUAppBar(
                title = if (contentType == ContentType.ListOnly)
                            uiState.listOnlyCurrentAppBarTitle
                        else uiState.listAndDetailCurrentAppBarTitle,
                canNavigateBack = viewModel.canNavigateBack(contentType),
                onNavigateBack = {
                    viewModel.navigateToPreviousScreen(contentType)
                }
            )
        }
    ) { innerPadding ->
        if (contentType == ContentType.ListAndDetail) {
            when (uiState.currentListAndDetailScreen) {
                ScreensForListAndDetailContentType.CategoriesAndRecommendations -> {
                    CategoriesAndRecommendationsList(
                        categories = LocalDataProvider.allRecommendations,
                        onCategoryClick = {
                            viewModel.selectCategory(it)
                        },
                        recommendations = uiState.selectedCategory.recommendations,
                        onRecommendationClick = {
                            viewModel.selectRecommendation(it)
                            viewModel.updateListAndDetailScreen(
                                ScreensForListAndDetailContentType.RecommendationsAndDetails
                            )
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                ScreensForListAndDetailContentType.RecommendationsAndDetails -> {
                    RecommendationsListAndRecommendationDetails(
                        recommendations = uiState.selectedCategory.recommendations,
                        onRecommendationClick = {
                            viewModel.selectRecommendation(it)
                        },
                        recommendation = uiState.selectedRecommendation,
                        currentImage = uiState.currentImgInRecommendation,
                        onBack = { viewModel.navigateToPreviousScreen(contentType) },
                        onPreviousImage = { viewModel.prevImgInRecommendation() },
                        onNextImage = { viewModel.nextImgInRecommendation() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        } else {
            when (uiState.currentListOnlyScreen) {
                ScreensForListOnlyContentType.Home -> {
                    CategoriesScreen(
                        categories = LocalDataProvider.allRecommendations,
                        onBack = { currentActivity.finish() },
                        onCategoryClick = { selectedCategory ->
                            viewModel.selectCategory(selectedCategory)
                            viewModel.updateListOnlyScreen(ScreensForListOnlyContentType.Recommendations)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                ScreensForListOnlyContentType.Recommendations -> {
                    RecommendationsScreen(
                        recommendations = uiState.selectedCategory.recommendations,
                        onBack = { viewModel.navigateToPreviousScreen(contentType) },
                        onRecommendationClick = { selectedRecommendation ->
                            viewModel.selectRecommendation(selectedRecommendation)
                            viewModel.updateListOnlyScreen(
                                ScreensForListOnlyContentType.RecommendationDetails
                            )
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
                ScreensForListOnlyContentType.RecommendationDetails -> {
                    RecommendationDetailsScreen(
                        recommendation = uiState.selectedRecommendation,
                        currentImage = uiState.currentImgInRecommendation,
                        onPreviousImage = { viewModel.prevImgInRecommendation() },
                        onNextImage = { viewModel.nextImgInRecommendation() },
                        onBack = { viewModel.navigateToPreviousScreen(contentType) },
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(dimensionResource(R.dimen.medium_padding))
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BagUAppBar(
    @StringRes title: Int?,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = if (title != null) stringResource(title) else "",
                style = MaterialTheme.typography.titleMedium
            )
        },
        // Change the color of the app bar
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier
    )
}