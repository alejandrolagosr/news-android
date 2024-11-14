package com.flagos.news.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
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
            onRetry = { viewModel.onUIEvent(HomeViewModel.UIEvent.OnGetNews) },
            onRefresh = { viewModel.onUIEvent(HomeViewModel.UIEvent.OnRefreshNews) },
            onItemRemove = { viewModel.onUIEvent(HomeViewModel.UIEvent.OnRemovedNews(it)) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    uiState: HomeViewModel.UIState = HomeViewModel.UIState(),
    onRetry: () -> Unit = {},
    onRefresh: () -> Unit = {},
    onItemRemove: (String) -> Unit = {}
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
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = onRefresh,
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn {
                        items(uiState.news, key = { it.id }) { news ->
                            NewsItems(
                                news = news,
                                onItemRemove = { onItemRemove(news.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshBox(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    state: PullToRefreshState = rememberPullToRefreshState(),
    contentAlignment: Alignment = Alignment.TopStart,
    indicator: @Composable BoxScope.() -> Unit = {
        Indicator(
            modifier = Modifier.align(Alignment.TopCenter),
            isRefreshing = isRefreshing,
            state = state
        )
    },
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier.pullToRefresh(state = state, isRefreshing = isRefreshing, onRefresh = onRefresh),
        contentAlignment = contentAlignment
    ) {
        content()
        indicator()
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    DevNewsTheme {
        HomeScreenContent()
    }
}