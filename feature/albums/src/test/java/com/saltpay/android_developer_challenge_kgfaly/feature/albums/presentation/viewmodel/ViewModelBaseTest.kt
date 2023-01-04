package com.saltpay.android_developer_challenge_kgfaly.feature.albums.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.saltpay.core.presentation.BaseViewModel
import com.saltpay.core.presentation.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class ViewModelBaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    protected lateinit var viewModel: BaseViewModel<*>

    protected lateinit var scheduler: TestCoroutineScheduler
    protected lateinit var dispatcher: TestDispatcher

    protected val uiStates = mutableListOf<UIState>()

    @Before
    open fun setUp() {
        scheduler = TestCoroutineScheduler()
        dispatcher = StandardTestDispatcher(scheduler)
        Dispatchers.setMain(dispatcher)
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
        uiStates.clear()
    }
}