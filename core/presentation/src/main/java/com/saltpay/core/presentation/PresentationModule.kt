package com.saltpay.core.presentation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class PresentationModule {

    companion object {
        @DispatchersIO
        @Provides
        fun provideIODispatcher() : CoroutineDispatcher = Dispatchers.IO

        @DispatchersMain
        @Provides
        fun provideMainDispatcher() : CoroutineDispatcher = Dispatchers.Main
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DispatchersIO

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DispatchersMain
}