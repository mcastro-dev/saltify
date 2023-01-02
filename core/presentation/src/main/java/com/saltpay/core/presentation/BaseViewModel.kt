package com.saltpay.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

abstract class BaseViewModel<S : UIState>(
    initialUiState: S
) : ViewModel() {

    protected val _uiState = MutableStateFlow(initialUiState)

    val uiState : StateFlow<S> = _uiState.asStateFlow()

    protected suspend fun setUiState(uiState: S) {
        withContext(Dispatchers.Main) {
            _uiState.value = uiState
        }
    }

    abstract fun onEvent(event: UIEvent)
}