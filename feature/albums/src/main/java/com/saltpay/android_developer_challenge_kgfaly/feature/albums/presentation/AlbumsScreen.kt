package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.R
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.composable.*
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel.*

@Composable
fun AlbumsScreen(
    viewModel: AlbumsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { AlbumsTopAppBar() }
    ) { paddingValues ->

        AlbumsContent(
            uiState = uiState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            onRefresh = { viewModel.onEvent(RefreshEvent()) },
            onSearchTextChanged = { viewModel.onEvent(SearchTopAlbumsEvent(it)) },
            onClearSearch = { viewModel.onEvent(ClearSearchEvent()) }
        )
    }
}

@Composable
private fun AlbumsTopAppBar() {
    TopAppBar(
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.albums_screen_title),
            style = typography.h6
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AlbumsContent(
    uiState: UIAlbumsState,
    modifier: Modifier = Modifier,
    onRefresh: (() -> Unit) = {},
    onSearchTextChanged: ((String) -> Unit) = {},
    onClearSearch: (() -> Unit) = {}
) {
    val refreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = onRefresh
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(refreshState)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchBar(
                searchText = uiState.searchText,
                placeholderText = stringResource(id = R.string.search_placeholder),
                onSearchTextChanged = onSearchTextChanged,
                onClearClicked = onClearSearch
            )

            if (uiState.isLoading) {
                Loading()
            } else {
                AlbumsList(uiState)
            }
        }

        PullRefreshIndicator(
            refreshing = uiState.isRefreshing,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}