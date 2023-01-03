package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.GetTopAlbumsUseCase
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.RefreshTopAlbumsUseCase
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.SearchTopAlbumsUseCase
import com.saltpay.core.domain.UnableToReachServerException
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
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val refreshTopAlbumsUseCase: RefreshTopAlbumsUseCase,
    private val searchTopAlbumsUseCase: SearchTopAlbumsUseCase
) : BaseViewModel<UIAlbumsState>(UIAlbumsState()) {

    init {
        onGetTopAlbumsEvent()
    }

    override fun onEvent(event: UIEvent) {
        when(event) {
            is GetTopAlbumsEvent -> onGetTopAlbumsEvent()
            is RefreshEvent -> onRefreshEvent()
            is SearchTopAlbumsEvent -> onSearchTopAlbumsEvent(event)
            is ClearSearchEvent -> onClearSearchEvent()
        }
    }

    private fun onClearSearchEvent() = viewModelScope.launch(dispatcher) {
        setUiState(_uiState.value.copy(searchText = ""))
        onGetTopAlbumsEvent()
    }

    private fun onSearchTopAlbumsEvent(
        event: SearchTopAlbumsEvent
    ) = viewModelScope.launch(dispatcher) {
        setUiState(_uiState.value.copy(isSearching = true, searchText = event.searchTerm))

        val searchResult = searchTopAlbumsUseCase(SearchTopAlbumsUseCase.Params(
            searchTerm = event.searchTerm
        ))
        searchResult.exceptionOrNull()?.let { error ->
            setUiState(
                _uiState.value.copy(isSearching = false, albums = emptyList(), error = error)
            )
            return@launch
        }

        val searchedAlbums = searchResult.getOrThrow()
        setUiState(
            _uiState.value.copy(isSearching = false, albums = searchedAlbums, error = null)
        )
    }

    private fun onRefreshEvent() = viewModelScope.launch(dispatcher) {
        setUiState(_uiState.value.copy(isRefreshing = true))

        val refreshResult = refreshTopAlbumsUseCase()
        refreshResult.exceptionOrNull()?.let { error ->
            setUiState(
                _uiState.value.copy(isRefreshing = false, albums = emptyList(), error = error)
            )
            return@launch
        }

        val albums = refreshResult.getOrThrow()
        setUiState(
            _uiState.value.copy(isRefreshing = false, albums = albums, error = null)
        )
    }

    private fun onGetTopAlbumsEvent() = viewModelScope.launch(dispatcher) {
        setUiState(
            _uiState.value.copy(isLoading = true, albums = emptyList())
        )

        val albumsResult = getTopAlbumsUseCase()
        albumsResult.exceptionOrNull()?.let { error ->
            setUiState(
                _uiState.value.copy(isLoading = false, error = error)
            )
            return@launch
        }

        val albums = albumsResult.getOrThrow()
        setUiState(
            _uiState.value.copy(isLoading = false, albums = albums, error = null)
        )
    }
}