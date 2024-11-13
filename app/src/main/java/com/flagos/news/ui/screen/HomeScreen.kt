package com.flagos.news.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.flagos.news.ui.components.ErrorMessage
import com.flagos.news.ui.components.LoadingIndicator
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
            uiState = viewModel.uiState,
            onRetry = { viewModel.onUIEvent(HomeViewModel.UIEvent.OnGetNews) }
        )
    }
}

@Composable
fun HomeScreenContent(
    uiState: HomeViewModel.UIState = HomeViewModel.UIState(),
    onRetry: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            uiState.errorMessage != null -> {
                ErrorMessage(
                    errorMessage = uiState.errorMessage,
                    onRetry = onRetry
                )
            }

            uiState.isLoading -> {
                LoadingIndicator(true)
            }

            else -> {
                LazyColumn {
                    items(uiState.news) { news ->
                        NewsItems(news = news)
                    }
                }
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