package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.list.viewmodel

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.core.presentation.UIState

data class UIAlbumsState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isSearching: Boolean = false,
    val searchText: String = "",
    val albums: List<Album> = emptyList(),
    val error: Throwable? = null,
) : UIState