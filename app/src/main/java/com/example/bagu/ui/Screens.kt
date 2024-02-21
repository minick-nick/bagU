package com.example.bagu.ui

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bagu.R
import com.example.bagu.data.LocalDataProvider
import com.example.bagu.data.Category
import com.example.bagu.model.Recommendation
import com.example.bagu.ui.theme.BagUTheme

@Composable
fun CategoriesScreen(
    categories: List<Category>,
    onBack: () -> Unit,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBack()
    }
    List(
        items = categories,
        onItemClick = onCategoryClick,
        modifier = modifier
            .padding(dimensionResource(R.dimen.medium_padding))
    )
}

@Composable
fun RecommendationsScreen(
    recommendations: List<Recommendation>,
    onBack: () -> Unit,
    onRecommendationClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBack()
    }
    List(
        items = recommendations,
        onItemClick = onRecommendationClick,
        modifier = modifier
            .padding(dimensionResource(R.dimen.medium_padding))
    )
}

@Composable
fun RecommendationDetailsScreen(
    recommendation: Recommendation,
    currentImage: Int,
    onBack: () -> Unit = {},
    onPreviousImage: () -> Unit,
    onNextImage: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBack()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        RecommendationImage(
            image = recommendation.images[currentImage],
            onPreviousImage = onPreviousImage,
            onNextImage = onNextImage
        )
        Text(
            text = stringResource(
                R.string.name,
                stringResource(recommendation.name)
            ),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.extra_large_padding))
        )
        Text(
            text = stringResource(
                R.string.address,
                stringResource(recommendation.address)
            ),
            style = MaterialTheme.typography.titleMedium,
        )
        if(recommendation.contactNumber != null) { // Mobile number will show if it's available
            Text(
                text = stringResource(
                    R.string.mobile_number,
                    stringResource(recommendation.contactNumber)
                ),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.extra_small_padding))
            )
        }
        Text(
            text = stringResource(recommendation.information),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.extra_large_padding))
        )
    }
}

@Composable
fun RecommendationImage(
    @DrawableRes image: Int,
    onPreviousImage: () -> Unit,
    onNextImage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
        )
        IconButton(
            onClick = onPreviousImage,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(50.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        IconButton(
            onClick = onNextImage,
            modifier
                .align(Alignment.CenterEnd)
                .size(50.dp)
        ) {
            Icon(
                Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun CategoriesAndRecommendationsList(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    recommendations: List<Recommendation>,
    onRecommendationClick: (Recommendation) -> Unit
) {
    Row(
        modifier = modifier
    ) {
        List(
            items = categories,
            onItemClick = onCategoryClick,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.medium_padding))
                .weight(1f)
        )
        List(
            items = recommendations,
            onItemClick = onRecommendationClick,
            modifier = Modifier
                .padding(
                    end = dimensionResource(R.dimen.medium_padding),
                    top = dimensionResource(R.dimen.medium_padding),
                    bottom = dimensionResource(R.dimen.medium_padding)
                )
                .weight(1f)
        )
    }
}

@Composable
fun RecommendationsListAndRecommendationDetails(
    modifier: Modifier = Modifier,
    recommendations: List<Recommendation>,
    onRecommendationClick: (Recommendation) -> Unit,
    onBack: () -> Unit,
    recommendation: Recommendation,
    currentImage: Int,
    onPreviousImage: () -> Unit,
    onNextImage: () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        List(
            items = recommendations,
            onItemClick = onRecommendationClick,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.large_padding))
                .weight(1f)
        )
        RecommendationDetailsScreen(
            recommendation = recommendation,
            currentImage = currentImage,
            onBack = onBack,
            onPreviousImage = onPreviousImage,
            onNextImage = onNextImage,
            modifier = Modifier
                .padding(
                    end = dimensionResource(R.dimen.medium_padding),
                    top = dimensionResource(R.dimen.medium_padding),
                    bottom = dimensionResource(R.dimen.medium_padding)
                )
                .weight(1f)
        )
    }
}

@Preview
@Composable
fun CategoriesScreenPreview() {
    BagUTheme() {
        Surface {
            CategoriesScreen(
                categories = LocalDataProvider.allRecommendations,
                onBack = {},
                onCategoryClick = {}
            )
        }
    }
}

@Preview
@Composable
fun RecommendationsScreenPreview() {
    BagUTheme() {
        Surface {
            RecommendationsScreen(
                recommendations = LocalDataProvider.allRecommendations[0].recommendations,
                onBack = {},
                onRecommendationClick = {}
            )
        }
    }
}

@Preview
@Composable
fun RecommendationDetailsScreenPreview() {
    BagUTheme() {
        Surface {
            RecommendationDetailsScreen(
                recommendation = LocalDataProvider.defaultRecommendation,
                currentImage = 0,
                onPreviousImage = { /*TODO*/ },
                onNextImage = { /*TODO*/ },
                modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))
            )
        }
    }
}

@Preview(widthDp = 1000)
@Composable
fun CategoriesAndRecommendationsListPreview() {
    BagUTheme() {
        Surface() {
            CategoriesAndRecommendationsList(
                categories = LocalDataProvider.allRecommendations,
                onCategoryClick = {},
                recommendations = LocalDataProvider.allRecommendations[0].recommendations,
                onRecommendationClick = {}
            )
        }
    }
}

@Preview(widthDp = 1000)
@Composable
fun RecommendationsListAndRecommendationDetailsPreview() {
    BagUTheme() {
        Surface() {
            RecommendationsListAndRecommendationDetails(
                recommendations = LocalDataProvider.allRecommendations[0].recommendations,
                onRecommendationClick = {},
                recommendation = LocalDataProvider.defaultRecommendation,
                currentImage = 0,
                onBack = {},
                onPreviousImage = { /*TODO*/ },
                onNextImage = { /*TODO*/ }
            )
        }
    }
}