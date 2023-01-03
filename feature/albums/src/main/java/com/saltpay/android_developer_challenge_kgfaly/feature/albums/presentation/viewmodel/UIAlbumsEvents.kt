package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel

import com.saltpay.core.presentation.UIEvent

class GetTopAlbumsEvent : UIEvent

class RefreshEvent : UIEvent

class ClearSearchEvent : UIEvent

data class SearchTopAlbumsEvent(
    val searchTerm: String
) : UIEvent