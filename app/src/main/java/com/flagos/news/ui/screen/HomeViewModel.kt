package com.flagos.news.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {

    var uiState by mutableStateOf(UIState())
        private set

    private fun fetchNews() = viewModelScope.launch(Dispatchers.IO) {
        getNewsByDateUseCase.invoke().collectLatest { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    uiState = uiState.copy(
                        news = result.data ?: emptyList(),
                        isLoading = false,
                        errorMessage = null
                    )
                }

                Status.ERROR -> {
                    uiState = uiState.copy(
                        news = emptyList(),
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                Status.LOADING -> {
                    uiState = uiState.copy(
                        news = emptyList(),
                        isLoading = true,
                        errorMessage = null
                    )
                }
            }
        }
    }

    data class UIState(
        val news: List<News> = emptyList(),
        val isLoading: Boolean = true,
        val errorMessage: String? = null
    )

    fun onUIEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.OnGetNews -> fetchNews()
        }
    }

    sealed class UIEvent {
        object OnGetNews : UIEvent()
    }
}