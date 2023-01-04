package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Artist
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.GetTopAlbumsUseCase
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.RefreshTopAlbumsUseCase
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.SearchTopAlbumsUseCase
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.list.viewmodel.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.willReturn
import java.time.LocalDate

@ExperimentalCoroutinesApi
class AlbumsViewModelTest : ViewModelBaseTest() {

    private lateinit var getTopAlbumsUseCase: GetTopAlbumsUseCase
    private lateinit var refreshTopAlbumsUseCase: RefreshTopAlbumsUseCase
    private lateinit var searchTopAlbumsUseCase: SearchTopAlbumsUseCase

    override fun setUp() {
        super.setUp()

        getTopAlbumsUseCase = mock()
        refreshTopAlbumsUseCase = mock()
        searchTopAlbumsUseCase = mock()
    }

    @Test
    fun `when initialized, then have expected initial uiState`() = runTest {
        val initialState = UIAlbumsState(
            isLoading = false,
            isRefreshing = false,
            isSearching = false,
            searchText = "",
            albums = emptyList(),
            error = null
        )
        initViewModel {}
        assertEquals(initialState, viewModel.uiState.value)
    }

    @Test
    fun `when GetTopAlbumsEvent on init, then uiState transitions correctly with the albums data`() = runTest {
        val albums = listOf(
            Album("1", "Some Testing Album", emptyList(), Artist("Artist"), "", LocalDate.now()),
        )
        initViewModel {
            given(getTopAlbumsUseCase.invoke()).willReturn {
                Result.success(albums)
            }
        }

        val job = launch(dispatcher) { viewModel.uiState.toList(uiStates) }

        advanceUntilIdle()

        assertEquals(3, uiStates.size)
        assertEquals(UIAlbumsState(), uiStates[0])
        assertEquals(
            UIAlbumsState(isLoading = true),
            uiStates[1]
        )
        assertEquals(
            UIAlbumsState(isLoading = false, albums = albums),
            uiStates[2]
        )
        job.cancel()
    }

    @Test
    fun `when RefreshEvent, then uiState transitions correctly with the albums data`() = runTest {
        val albums = listOf(
            Album("1", "Some Testing Album", emptyList(), Artist("Artist"), "", LocalDate.now()),
        )
        initViewModel {
            given(getTopAlbumsUseCase.invoke()).willReturn {
                Result.success(albums)
            }
            given(refreshTopAlbumsUseCase.invoke()).willReturn {
                Result.success(albums)
            }
        }

        // Advance to ignore self launched events
        advanceUntilIdle()

        val job = launch(dispatcher) { viewModel.uiState.toList(uiStates) }

        viewModel.onEvent(RefreshEvent())

        advanceUntilIdle()

        assertEquals(3, uiStates.size)
        assertEquals(
            UIAlbumsState(albums = albums),
            uiStates[0]
        )
        assertEquals(
            UIAlbumsState(isRefreshing = true, albums = albums),
            uiStates[1]
        )
        assertEquals(
            UIAlbumsState(isRefreshing = false, albums = albums),
            uiStates[2]
        )
        job.cancel()
    }

    @Test
    fun `when SearchTopAlbumsEvent, then uiState transitions correctly with the albums data`() = runTest {
        val albums = listOf(
            Album("1", "Some Testing Album", emptyList(), Artist("Artist"), "", LocalDate.now()),
            Album("2", "Some Other Album", emptyList(), Artist("Artist"), "", LocalDate.now()),
        )
        val searchedAlbums = listOf(
            Album("2", "Some Other Album", emptyList(), Artist("Artist"), "", LocalDate.now()),
        )
        val searchTerm = "other"
        initViewModel {
            given(getTopAlbumsUseCase.invoke()).willReturn {
                Result.success(albums)
            }
            given(searchTopAlbumsUseCase.invoke(any())).willReturn {
                Result.success(searchedAlbums)
            }
        }

        // Advance to ignore self launched events
        advanceUntilIdle()

        val job = launch(dispatcher) { viewModel.uiState.toList(uiStates) }

        viewModel.onEvent(SearchTopAlbumsEvent(searchTerm))

        advanceUntilIdle()

        assertEquals(3, uiStates.size)
        assertEquals(
            UIAlbumsState(albums = albums),
            uiStates[0]
        )
        assertEquals(
            UIAlbumsState(isSearching = true, searchText = searchTerm, albums = albums),
            uiStates[1]
        )
        assertEquals(
            UIAlbumsState(isSearching = false, searchText = searchTerm, albums = searchedAlbums),
            uiStates[2]
        )
        job.cancel()
    }

    @Test
    fun `when ClearSearchEvent, then uiState transitions correctly with the albums data`() = runTest {
        val albums = listOf(
            Album("1", "Some Testing Album", emptyList(), Artist("Artist"), "", LocalDate.now()),
            Album("2", "Some Other Album", emptyList(), Artist("Artist"), "", LocalDate.now()),
        )
        initViewModel {
            given(getTopAlbumsUseCase.invoke()).willReturn {
                Result.success(albums)
            }
        }

        // Advance to ignore self launched events
        advanceUntilIdle()

        val job = launch(dispatcher) { viewModel.uiState.toList(uiStates) }

        viewModel.onEvent(ClearSearchEvent())

        advanceUntilIdle()

        assertEquals(3, uiStates.size)
        assertEquals(
            UIAlbumsState(albums = albums),
            uiStates[0]
        )
        assertEquals(
            UIAlbumsState(isLoading = true, searchText = ""),
            uiStates[1]
        )
        assertEquals(
            UIAlbumsState(isLoading = false, albums = albums),
            uiStates[2]
        )
        job.cancel()
    }

    private inline fun initViewModel(before: () -> Unit) {
        before.invoke()
        viewModel = AlbumsViewModel(
            dispatcher = dispatcher,
            getTopAlbumsUseCase = getTopAlbumsUseCase,
            refreshTopAlbumsUseCase = refreshTopAlbumsUseCase,
            searchTopAlbumsUseCase = searchTopAlbumsUseCase
        )
    }
}