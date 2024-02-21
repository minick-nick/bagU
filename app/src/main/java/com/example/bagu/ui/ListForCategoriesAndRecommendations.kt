package com.example.bagu.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.example.bagu.model.Recommendation
import com.example.bagu.ui.theme.BagUTheme
import com.example.bagu.data.Category

@Composable
fun <T> List(
    items: List<T>,
    onItemClick: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        //contentPadding = PaddingValues(dimensionResource(R.dimen.medium_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
        modifier = modifier
    ) {
        items(items) { item: T ->
            ListItemCard(
                item = item,
                onItemClick = onItemClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> ListItemCard(
    item: T,
    onItemClick: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onItemClick(item) },
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(128.dp)
                .padding(dimensionResource(R.dimen.medium_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ListImageItem(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.card_image_width)),
                image = when (item) {
                    is Category -> item.image
                    is Recommendation -> item.images[0]
                    else -> R.drawable.coffee_cup_coffee_svgrepo_com
                }
            )
            Spacer(
                modifier = Modifier
                    .width(dimensionResource(R.dimen.medium_padding))
            )
            Text(
                text = stringResource(
                    when (item) {
                        is Category -> item.name
                        is Recommendation -> item.name
                        else -> R.drawable.coffee_cup_coffee_svgrepo_com
                    }
                ),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
fun ListImageItem(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Preview()
@Composable
fun CategoryListItemPreview() {
    BagUTheme() {
            val category = LocalDataProvider.defaultCategory
            ListItemCard(
                item = category,
                onItemClick = {}
            )
    }
}

@Preview
@Composable
fun RecommendationListItemPreview() {
    BagUTheme() {
        val recommendation = Recommendation(
            name = R.string.cafe_de_angelo,
            information = R.string.cafe_de_angelo_info,
            address = R.string.cafe_de_angelo_address,
            contactNumber = R.string.cafe_de_angelo_contact_number,
            images = listOf(
                R.drawable.cafe_de_angelo_1,
                R.drawable.cafe_de_angelo_2)
        )

        ListItemCard(
            item = recommendation,
            onItemClick = {})
    }
}