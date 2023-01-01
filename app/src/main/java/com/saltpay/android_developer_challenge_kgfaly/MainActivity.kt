package com.saltpay.android_developer_challenge_kgfaly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.AlbumsScreen
import com.saltpay.android_developer_challenge_kgfaly.ui.theme.AndroiddeveloperchallengekgfalyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroiddeveloperchallengekgfalyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AlbumsScreen()
                }
            }
        }
    }
}