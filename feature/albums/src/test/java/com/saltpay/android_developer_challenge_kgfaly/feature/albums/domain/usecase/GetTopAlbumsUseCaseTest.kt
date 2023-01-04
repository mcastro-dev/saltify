package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class GetTopAlbumsUseCaseTest {

    private lateinit var albumsRepository: AlbumsRepository
    private lateinit var getAlbumsLimitUseCase: GetAlbumsLimitUseCase
    private lateinit var sut: GetTopAlbumsUseCaseImpl

    @Before
    fun setUp() {
        albumsRepository = mock()
        getAlbumsLimitUseCase = mock(defaultAnswer = { 100 })
        sut = GetTopAlbumsUseCaseImpl(albumsRepository, getAlbumsLimitUseCase)
    }

    @Test
    fun `when repository returns list of albums, return the list of albums`() = runBlocking {
        val albums: List<Album> = emptyList()
        given(albumsRepository.getTopAlbums(any())).willReturn(Result.success(albums))

        val whenResult = sut()

        then(albumsRepository).should(times(1)).getTopAlbums(any())
        assertEquals(albums, whenResult.getOrNull())
    }

    @Test
    fun `when repository returns failure, return the failure`() = runBlocking {
        val error = RuntimeException()
        given(albumsRepository.getTopAlbums(any())).willReturn(Result.failure(error))

        val whenResult = sut()

        then(albumsRepository).should(times(1)).getTopAlbums(any())
        assertEquals(error, whenResult.exceptionOrNull())
    }
}