package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme.typography
import androidx.hilt.navigation.compose.hiltViewModel
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel.AlbumsViewModel
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel.GetTopAlbumsEvent

@Composable
fun AlbumsScreen(
    viewModel: AlbumsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                Text(text = "SaltPay", style = typography.h6)
            }
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
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
    }
}

@Composable
private fun AlbumsContent(albums: List<Album>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
    ) {
        items(
            items = albums,
            itemContent = { AlbumListItem(album = it) }
        )
    }
}

@Composable
private fun EmptyContent() {

}

@Composable
private fun Loading() {
    Text("Loading...")
}