package com.saltpay.android_developer_challenge_kgfaly.feature.albums

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.AlbumsRepositoryImpl
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.AlbumsRemoteDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.source.iTunesAlbumsApi
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.source.iTunesAlbumsDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.GetTopAlbumsUseCase
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.GetTopAlbumsUseCaseImpl
import com.saltpay.network.RestApiProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AlbumsModule {

    companion object {
        @Provides
        @Singleton
        fun provideITunesAlbumsApi(restApiProvider: RestApiProvider): iTunesAlbumsApi {
            return restApiProvider.provide(iTunesAlbumsApi::class.java)
        }
    }

    @Binds
    abstract fun bindAlbumsRemoteDataSource(impl: iTunesAlbumsDataSource) : AlbumsRemoteDataSource

    @Binds
    abstract fun bindAlbumsRepository(impl: AlbumsRepositoryImpl) : AlbumsRepository

    @Binds
    abstract fun bindGetTopAlbumsUseCase(impl: GetTopAlbumsUseCaseImpl) : GetTopAlbumsUseCase
}