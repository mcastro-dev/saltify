package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.list.composable

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.common.AlbumImage

@Composable
fun AlbumListItem(album: Album, onClicked: ((Album) -> Unit)? = null) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .fillMaxWidth()
            .clickable { onClicked?.invoke(album) },
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Row {
            AlbumImage(
                album = album,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(12.dp)))
            )
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