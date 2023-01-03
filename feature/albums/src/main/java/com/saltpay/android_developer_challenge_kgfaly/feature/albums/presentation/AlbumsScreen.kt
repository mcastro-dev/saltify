package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.composable.AlbumListItem
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.composable.Error
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel.AlbumsViewModel
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel.RefreshEvent
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel.UIAlbumsState

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (uiState.isLoading) {
                Loading()
            } else {
                AlbumsContent(
                    uiState = uiState,
                    onRefresh = { viewModel.onEvent(RefreshEvent()) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AlbumsContent(
    uiState: UIAlbumsState,
    onRefresh: (() -> Unit)
) {
    val refreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = onRefresh
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(refreshState)
    ) {
        uiState.error?.let {
            Error(it)
        }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
        ) {
            items(
                items = uiState.albums,
                key = { it.id },
                itemContent = { AlbumListItem(album = it) }
            )
        }

        PullRefreshIndicator(
            refreshing = uiState.isRefreshing,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}