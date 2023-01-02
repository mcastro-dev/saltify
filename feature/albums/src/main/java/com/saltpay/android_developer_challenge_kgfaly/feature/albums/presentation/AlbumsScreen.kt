package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel.AlbumsViewModel
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel.GetTopAlbumsEvent

@Composable
fun AlbumsScreen(
    viewModel: AlbumsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = "GET_TOP_ALBUMS") {
        viewModel.onEvent(GetTopAlbumsEvent())
    }

    if (uiState.isLoading) {
        Loading()
    } else {
        if (uiState.albums.isEmpty()) {
            EmptyContent()
        } else {
            AlbumsContent(uiState.albums)
        }
    }
}

@Composable
private fun AlbumsContent(albums: List<Album>) {
    Text(albums.first().title)
}

@Composable
private fun EmptyContent() {

}

@Composable
private fun Loading() {
    Text("Loading...")
}