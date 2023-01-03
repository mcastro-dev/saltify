package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album

@Composable
fun AlbumListItem(album: Album) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Row {
            AlbumImage(album)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = album.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = typography.body1
                )
                Text(
                    text = album.artist.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = typography.body2
                )
            }
        }
    }
}

@Composable
private fun AlbumImage(album: Album) {
    AsyncImage(
        model = album.largestImage.url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(90.dp)
            .clip(RoundedCornerShape(corner = CornerSize(12.dp)))
    )
}