package com.flagos.news.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flagos.domain.interactor.GetMarkItemAsRemovedUseCase
import com.flagos.domain.interactor.GetNewsByDateUseCase
import com.flagos.domain.model.News
import com.flagos.domain.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsByDateUseCase: GetNewsByDateUseCase,
    private val getMarkItemAsRemovedUseCase: GetMarkItemAsRemovedUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(UIState())
        private set

    private fun fetchNews(isRefreshing: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        uiState = uiState.copy(isRefreshing = isRefreshing)
        getNewsByDateUseCase.invoke().collectLatest { result ->
            uiState = when (result.status) {
                Status.SUCCESS -> {
                    uiState.copy(
                        news = result.data ?: emptyList(),
                        isLoading = false,
                        errorMessage = null,
                        isRefreshing = false
                    )
                }

                Status.ERROR -> {
                    uiState.copy(
                        news = emptyList(),
                        isLoading = false,
                        errorMessage = result.message,
                        isRefreshing = false
                    )
                }

                Status.LOADING -> {
                    uiState.copy(
                        isLoading = true,
                        isRefreshing = false
                    )
                }
            }
        }
    }

    private fun removeNewsItem(newsId: String) {
        uiState = uiState.copy(news = uiState.news.filter { it.id != newsId })
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Bambino", "removeNewsItem: $newsId")
            getMarkItemAsRemovedUseCase.invoke(newsId)
        }
    }

    data class UIState(
        val news: List<News> = emptyList(),
        val isLoading: Boolean = true,
        val errorMessage: String? = null,
        val isRefreshing: Boolean = false,
    )

    fun onUIEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.OnGetNews -> fetchNews(false)
            is UIEvent.OnRefreshNews -> fetchNews(true)
            is UIEvent.OnRemovedNews -> removeNewsItem(uiEvent.newsId)
        }
    }

    sealed class UIEvent {
        object OnGetNews : UIEvent()
        object OnRefreshNews : UIEvent()
        data class OnRemovedNews(val newsId: String) : UIEvent()
    }
}