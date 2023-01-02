package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.GetTopAlbumsUseCase
import com.saltpay.core.presentation.BaseViewModel
import com.saltpay.core.presentation.PresentationModule
import com.saltpay.core.presentation.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    @PresentationModule.DispatchersIO
    private val dispatcher: CoroutineDispatcher,
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase
) : BaseViewModel<UIAlbumsState>(UIAlbumsState()) {

    override fun onEvent(event: UIEvent) {
        when(event) {
            is GetTopAlbumsEvent -> onGetTopAlbumsEvent()
        }
    }

    private fun onGetTopAlbumsEvent() = viewModelScope.launch(dispatcher) {
        setUiState(
            _uiState.value.copy(isLoading = true, albums = emptyList())
        )

        val albumsResult = getTopAlbumsUseCase()
        if (albumsResult.isFailure) {
            //TODO: handle failure
            return@launch
        }

        val albums = albumsResult.getOrThrow()

        setUiState(
            _uiState.value.copy(isLoading = false, albums = albums)
        )
    }
}