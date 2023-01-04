package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.local

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.local.inmemory.InMemoryAlbumsDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Artist
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class InMemoryAlbumsDataSourceTest {

    private val fakeAlbums: List<Album> = listOf(
        Album("1", "Some Testing Album", emptyList(), Artist("Artist"), "", LocalDate.now()),
        Album("2", "Another Album", emptyList(), Artist("Artist"), "", LocalDate.now()),
    )

    private lateinit var sut: InMemoryAlbumsDataSource

    @Before
    fun setUp() {
        sut = InMemoryAlbumsDataSource()
    }

    @Test
    fun `given has albums stored, when getTopAlbums, then return albums`() = runBlocking {
        sut.topAlbums.addAll(fakeAlbums)

        val whenResult = sut.getTopAlbums(limit = 100)

        assertEquals(fakeAlbums, whenResult)
    }

    @Test
    fun `given has albums to store, when saveTopAlbums, then store albums`() = runBlocking {
        sut.saveTopAlbums(fakeAlbums)

        assertEquals(fakeAlbums, sut.topAlbums.toList())
    }

    @Test
    fun `given has albums stored, when deleteAllTopAlbums, then delete albums`() = runBlocking {
        sut.topAlbums.addAll(fakeAlbums)

        sut.deleteAllTopAlbums()

        assertEquals(emptyList<Album>(), sut.topAlbums.toList())
    }
}