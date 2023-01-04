package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.R
import com.saltpay.core.domain.error.UnableToReachServerException

@Composable
fun Error(error: Throwable) {
    val errorMessage = when(error) {
        is UnableToReachServerException -> stringResource(R.string.error_unreachable_server)
        else -> stringResource(R.string.error_default)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(errorMessage, modifier = Modifier.padding(10.dp))
    }
}