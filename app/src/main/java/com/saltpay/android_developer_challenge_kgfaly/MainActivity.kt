package com.saltpay.android_developer_challenge_kgfaly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Artist
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.details.AlbumDetailScreen
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.list.AlbumsListScreen
import com.saltpay.android_developer_challenge_kgfaly.ui.theme.AndroiddeveloperchallengekgfalyTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroiddeveloperchallengekgfalyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
private fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "albums_list_screen"
    ) {
        composable(route = "albums_list_screen") {
            AlbumsListScreen(navController = navController)
        }
        composable(
            route = "album_detail_screen/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.StringType })
        ) {
            it.arguments?.getString("albumId")?.let { albumId ->
                AlbumDetailScreen(albumId = albumId, navController = navController)
            }
        }
    }
}