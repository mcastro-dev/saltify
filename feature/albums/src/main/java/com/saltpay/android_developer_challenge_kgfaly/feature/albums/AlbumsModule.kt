package com.saltpay.android_developer_challenge_kgfaly.feature.albums

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.AlbumsRepositoryImpl
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.local.AlbumsLocalDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.local.inmemory.InMemoryAlbumsDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.AlbumsRemoteDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.source.ITunesAlbumsDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.source.ITunesAlbumsApi
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase.*
import com.saltpay.core.infrastructure.RestApiProvider
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
        fun provideITunesAlbumsApi(restApiProvider: RestApiProvider): ITunesAlbumsApi {
            return restApiProvider.provide(ITunesAlbumsApi::class.java)
        }
    }

    @Binds
    abstract fun bindAlbumsRemoteDataSource(impl: ITunesAlbumsDataSource) : AlbumsRemoteDataSource

    @Binds
    abstract fun bindAlbumsLocalDataSource(impl: InMemoryAlbumsDataSource) : AlbumsLocalDataSource

    @Binds
    @Singleton
    abstract fun bindAlbumsRepository(impl: AlbumsRepositoryImpl) : AlbumsRepository

    @Binds
    abstract fun bindGetTopAlbumsUseCase(impl: GetTopAlbumsUseCaseImpl) : GetTopAlbumsUseCase

    @Binds
    abstract fun bindRefreshTopAlbumsUseCase(impl: RefreshTopAlbumsUseCaseImpl) : RefreshTopAlbumsUseCase

    @Binds
    abstract fun bindSearchTopAlbumsUseCase(impl: SearchTopAlbumsUseCaseImpl) : SearchTopAlbumsUseCase

    @Binds
    abstract fun bindGetAlbumsLimitUseCase(impl: GetAlbumsLimitUseCaseImpl) : GetAlbumsLimitUseCase

    @Binds
    abstract fun bindGetAlbumByIdUseCase(impl: GetAlbumByIdUseCaseImpl) : GetAlbumByIdUseCase
}