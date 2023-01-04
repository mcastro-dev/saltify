package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album

@Composable
fun AlbumImage(album: Album, modifier: Modifier = Modifier) {
    AsyncImage(
        model = album.largestImage?.url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}