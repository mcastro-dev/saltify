package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.details.viewmodel

import com.saltpay.core.presentation.UIEvent

data class GetAlbumEvent(
    val albumId: String
) : UIEvent