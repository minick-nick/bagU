package com.example.bagu.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bagu.ui.theme.BagUTheme

@Composable
fun RecommendationsScreen(
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
fun RecommendationsScreenPreview() {
    BagUTheme() {
        Surface {
            RecommendationsScreen()
        }
    }
}