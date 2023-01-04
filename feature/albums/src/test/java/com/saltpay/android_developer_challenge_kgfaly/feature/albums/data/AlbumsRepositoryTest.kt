package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.local.AlbumsLocalDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.AlbumsRemoteDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Artist
import com.saltpay.core.domain.error.UnableToReachServerException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import java.net.SocketException
import java.net.UnknownHostException

class AlbumsRepositoryTest {

    private lateinit var remoteDataSource: AlbumsRemoteDataSource
    private lateinit var localDataSource: AlbumsLocalDataSource
    private lateinit var sut: AlbumsRepositoryImpl

    @Before
    fun setUp() {
        remoteDataSource = mock()
        localDataSource = mock()
        sut = AlbumsRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `when getTopAlbums, then get remote data, refresh local data, and return the albums`() = runBlocking {
        val albums: List<Album> = emptyList()
        given(remoteDataSource.getTopAlbums(any())).willReturn(albums)

        val whenResult = sut.getTopAlbums(limit = 100)

        then(remoteDataSource).should(times(1)).getTopAlbums(any())
        then(localDataSource).should(times(1)).deleteAllTopAlbums()
        then(localDataSource).should(times(1)).saveTopAlbums(albums)
        assertEquals(albums, whenResult.getOrNull())
    }

    @Test
    fun `given remoteDataSource throws UnknownHostException, when getTopAlbums, then return Failure with UnableToReachServerException`() = runBlocking {
        val unknownHostException = UnknownHostException()
        given(remoteDataSource.getTopAlbums(any())).willAnswer { throw unknownHostException }

        val whenResult = sut.getTopAlbums(limit = 100)

        then(remoteDataSource).should(times(1)).getTopAlbums(any())
        then(localDataSource).should(times(0)).deleteAllTopAlbums()
        then(localDataSource).should(times(0)).saveTopAlbums(any())
        assertEquals(UnableToReachServerException::class, whenResult.exceptionOrNull()!!::class)
    }

    @Test
    fun `given remoteDataSource throws SocketException, when getTopAlbums, then return Failure with UnableToReachServerException`() = runBlocking {
        val socketException = SocketException()
        given(remoteDataSource.getTopAlbums(any())).willAnswer { throw socketException }

        val whenResult = sut.getTopAlbums(limit = 100)

        then(remoteDataSource).should(times(1)).getTopAlbums(any())
        then(localDataSource).should(times(0)).deleteAllTopAlbums()
        then(localDataSource).should(times(0)).saveTopAlbums(any())
        assertEquals(UnableToReachServerException::class, whenResult.exceptionOrNull()!!::class)
    }

    @Test
    fun `when getTopAlbumsByTitle, then get local data and filter by title, return filtered albums`() = runBlocking {
        val albums: List<Album> = listOf(
            Album("1", "Some Testing Album", emptyList(), Artist("Artist")),
            Album("2", "Another Album", emptyList(), Artist("Artist")),
        )
        given(localDataSource.getTopAlbums(any())).willReturn(albums)

        val whenResult = sut.getTopAlbumsByTitle(query = "test", limit = 100)

        then(localDataSource).should(times(1)).getTopAlbums(any())
        assertEquals(albums.subList(0, 1), whenResult.getOrNull())
    }

    @Test
    fun `given localDataSource throws error, when getTopAlbumsByTitle, then throw the error`() = runBlocking {
        val error = RuntimeException()
        given(localDataSource.getTopAlbums(any())).willThrow(error)

        Assert.assertThrows(RuntimeException::class.java) {
            runBlocking {
                sut.getTopAlbumsByTitle(query = "test", limit = 100)
            }
        }

        then(localDataSource).should(times(1)).getTopAlbums(any())
        Unit
    }
}
