package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.core.presentation.UIState

data class UIAlbumsState(
    val isLoading: Boolean = false,
    val albums: List<Album> = emptyList()
) : UIState