package com.flagos.news.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.flagos.domain.model.News
import com.flagos.news.ui.components.NewsItems
import com.flagos.news.ui.theme.DevNewsTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        viewModel.onUIEvent(HomeViewModel.UIEvent.OnGetNews)
    }

    DevNewsTheme {
        HomeScreenContent(
            uiState = viewModel.uiState
        )
    }
}

@Composable
fun HomeScreenContent(
    uiState: HomeViewModel.UIState = HomeViewModel.UIState(),
) {
    LazyColumn {
        items(uiState.news) { news ->
            NewsItems(news = news)
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