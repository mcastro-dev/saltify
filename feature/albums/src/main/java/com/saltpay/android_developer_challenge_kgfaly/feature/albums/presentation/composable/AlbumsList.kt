package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel.UIAlbumsState

@Composable
fun AlbumsList(
    uiState: UIAlbumsState,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        uiState.error?.let {
            Error(it)
        }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(
                items = uiState.albums,
                key = { it.id },
                itemContent = { AlbumListItem(album = it) }
            )
        }
    }
}