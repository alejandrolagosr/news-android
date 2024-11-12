package com.flagos.news.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.flagos.domain.model.News
import com.flagos.news.ui.components.NewsItems
import com.flagos.news.ui.theme.DevNewsTheme

@Composable
fun HomeScreen() {
    DevNewsTheme {
        HomeScreenContent()
    }
}

@Composable
fun HomeScreenContent() {
    LazyColumn {
        for (i in 0..10) {
            item {
                NewsItems(
                    news = News(
                        id = i,
                        title = "Title $i",
                        author = "Author $i",
                        url = "www.google.com",
                        createdAt = "23 min ago"
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    DevNewsTheme {
        HomeScreenContent()
    }
}