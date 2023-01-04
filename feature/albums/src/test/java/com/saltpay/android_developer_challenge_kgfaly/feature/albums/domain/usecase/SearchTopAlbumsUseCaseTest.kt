package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.SearchTopAlbumsUseCase
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.SearchTopAlbumsUseCaseImpl
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class SearchTopAlbumsUseCaseTest {

    private lateinit var albumsRepository: AlbumsRepository
    private lateinit var getAlbumsLimitUseCase: GetAlbumsLimitUseCase
    private lateinit var sut: SearchTopAlbumsUseCaseImpl

    @Before
    fun setUp() {
        albumsRepository = mock()
        getAlbumsLimitUseCase = mock(defaultAnswer = { 100 })
        sut = SearchTopAlbumsUseCaseImpl(albumsRepository, getAlbumsLimitUseCase)
    }

    @Test
    fun `given searchTerm, when repository returns list of albums, return the list of albums`() = runBlocking {
        val albums: List<Album> = emptyList()
        given(albumsRepository.getTopAlbumsByTitle(any(), any())).willReturn(Result.success(albums))

        val whenResult = sut(SearchTopAlbumsUseCase.Params(searchTerm = "test"))

        then(albumsRepository).should(times(1)).getTopAlbumsByTitle(any(), any())
        assertEquals(albums, whenResult.getOrNull())
    }

    @Test
    fun `given searchTerm, when repository returns failure, return the failure`() = runBlocking {
        val error = RuntimeException()
        given(albumsRepository.getTopAlbumsByTitle(any(), any())).willReturn(Result.failure(error))

        val whenResult = sut(SearchTopAlbumsUseCase.Params(searchTerm = "test"))

        then(albumsRepository).should(times(1)).getTopAlbumsByTitle(any(), any())
        assertEquals(error, whenResult.exceptionOrNull())
    }
}