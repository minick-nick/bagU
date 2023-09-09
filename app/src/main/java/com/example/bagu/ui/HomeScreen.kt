package com.example.bagu.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bagu.R
import com.example.bagu.data.LocalCategoriesDataProvider
import com.example.bagu.model.Category
import com.example.bagu.ui.theme.BagUTheme

@Composable
fun HomeScreen(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(R.dimen.medium_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
        modifier = modifier
    ) {
        items(categories) {
            CategoriesListItem(
                category = it,
                onItemClick = onCategoryClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesListItem(
    category: Category,
    onItemClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onItemClick(category) },
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(128.dp)
                .padding(dimensionResource(R.dimen.medium_padding))
        ) {
            CategoriesListImageItem(
                modifier = Modifier.width(dimensionResource(R.dimen.card_image_height)),
                image = category.image
            )
            Spacer(
                modifier = Modifier
                    .width(dimensionResource(R.dimen.medium_padding))
            )
            Text(
                text = stringResource(category.name),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun CategoriesListImageItem(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(image),
            contentDescription = null
        )
    }
}

@Preview(showBackground = false)
@Composable
fun CategoryListItemPreview() {
    BagUTheme() {
            val category = Category(
                name = R.string.coffee_shops,
                image = R.drawable.coffee_cup_coffee_svgrepo_com
            )
            CategoriesListItem(
                category = category,
                onItemClick = {}
            )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    BagUTheme() {
        Surface {
            HomeScreen(
                categories = LocalCategoriesDataProvider.allCategories,
                onCategoryClick = {}
            )
        }
    }
}
