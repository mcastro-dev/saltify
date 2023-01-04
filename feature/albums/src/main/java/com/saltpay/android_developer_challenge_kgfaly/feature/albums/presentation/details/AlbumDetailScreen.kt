package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.details

import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.R
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.details.viewmodel.AlbumDetailsViewModel
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.details.viewmodel.UIDetailsState
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.common.AlbumImage
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.details.viewmodel.GetAlbumEvent
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.list.composable.Loading

@Composable
fun AlbumDetailScreen(
    albumId: String,
    navController: NavController? = null,
    viewModel: AlbumDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(GetAlbumEvent(albumId))
    }

    Scaffold { paddingValues ->
        AlbumDetailContent(
            uiState = uiState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            onBackClicked = {
                navController?.navigateUp()
            }
        )
    }
}

@Composable
private fun AlbumDetailContent(
    uiState: UIDetailsState,
    modifier: Modifier = Modifier,
    onBackClicked: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
    ) {
        if (uiState.isLoading) {
            Loading()
        } else {
            uiState.album?.let {
                AlbumDetails(album = it, onBackClicked = onBackClicked)
            }
        }
    }
}

@Composable
private fun AlbumDetails(
    album: Album,
    onBackClicked: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth()
        ) {
            AlbumImage(
                album = album,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(16.dp)
            )

            AlbumImage(
                album = album,
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(12.dp)))
                    .align(Alignment.Center)
            )

            IconButton(
                modifier = Modifier
                    .width(36.dp)
                    .align(Alignment.TopStart),
                onClick = { onBackClicked?.invoke() }
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = Color.Black.copy(alpha = 0.2f))
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Section(
                title = stringResource(id = R.string.details_section_album),
                icon = Icons.Filled.List,
                value = album.title
            )
            Spacer(modifier = Modifier.height(20.dp))
            Section(
                title = stringResource(id = R.string.details_section_artist),
                icon = Icons.Filled.Star,
                value = album.artist.name
            )
            Spacer(modifier = Modifier.height(20.dp))
            Section(
                title = stringResource(id = R.string.details_section_release_date),
                icon = Icons.Filled.DateRange,
                value = album.releaseDate.toString()
            )
            Spacer(modifier = Modifier.height(60.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(album.link))
                    context.startActivity(urlIntent)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.album_link_button),
                    modifier = Modifier.padding(10.dp),
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
private fun Section(title: String, icon: ImageVector, value: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(title, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(value)
    }
}