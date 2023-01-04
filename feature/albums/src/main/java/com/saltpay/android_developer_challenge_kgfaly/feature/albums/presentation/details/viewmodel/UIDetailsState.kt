package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.details.viewmodel

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.core.presentation.UIState

data class UIDetailsState(
    val isLoading: Boolean = false,
    val album: Album? = null,
) : UIState