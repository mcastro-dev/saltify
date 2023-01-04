package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.Artist
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.Feed
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.Response
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.source.ITunesAlbumsApi
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.source.ITunesAlbumsDataSource
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import java.net.SocketException
import java.time.LocalDate

class ITunesAlbumsDataSourceTest {

    private lateinit var albumsApi: ITunesAlbumsApi
    private lateinit var sut: ITunesAlbumsDataSource

    @Before
    fun setUp() {
        albumsApi = mock()
        sut = ITunesAlbumsDataSource(albumsApi)
    }

    @Test
    fun `given albumsApi returns a response, when getTopAlbums, then map to domain and return albums`() = runBlocking {
        val response = Response(
            feed = Feed(
                albums = listOf(
                    Album(id = "1", name = "Album 1", images = emptyList(), artist = Artist("Artist"), link = "", releaseDate = LocalDate.now()),
                    Album(id = "2", name = "Album 2", images = emptyList(), artist = Artist("Artist"), link = "", releaseDate = LocalDate.now()),
                )
            )
        )
        given(albumsApi.getTopAlbumsFeed(any())).willReturn(response)

        val whenResult = sut.getTopAlbums(limit = 100)

        val expectedResult = response.feed.albums.map { it.toDomain() }
        assertEquals(expectedResult, whenResult)
    }

    @Test
    fun `given albumsApi throws an error, when getTopAlbums, then throw the error`() = runBlocking {
        given(albumsApi.getTopAlbumsFeed(any())).willAnswer { throw SocketException() }

        Assert.assertThrows(SocketException::class.java) {
            runBlocking {
                sut.getTopAlbums(limit = 100)
            }
        }
        Unit
    }
}