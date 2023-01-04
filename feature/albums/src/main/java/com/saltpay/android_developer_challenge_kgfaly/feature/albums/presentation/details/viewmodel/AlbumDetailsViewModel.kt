package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.details.viewmodel

import androidx.lifecycle.viewModelScope
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.GetAlbumByIdUseCase
import com.saltpay.core.presentation.BaseViewModel
import com.saltpay.core.presentation.PresentationModule
import com.saltpay.core.presentation.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    @PresentationModule.DispatchersIO
    private val dispatcher: CoroutineDispatcher,
    private val getAlbumByIdUseCase: GetAlbumByIdUseCase
) : BaseViewModel<UIDetailsState>(UIDetailsState()) {

    override fun onEvent(event: UIEvent) {
        when(event) {
            is GetAlbumEvent -> onGetAlbumEvent(event)
        }
    }

    private fun onGetAlbumEvent(
        event: GetAlbumEvent
    ) = viewModelScope.launch(dispatcher) {
        setUiState(_uiState.value.copy(isLoading = true, album = null))

        val result = getAlbumByIdUseCase(GetAlbumByIdUseCase.Params(albumId = event.albumId))
        val album = result.getOrThrow()

        setUiState(_uiState.value.copy(isLoading = false, album = album))
    }

}