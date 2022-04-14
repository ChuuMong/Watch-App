package com.chuumong.watch.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container


abstract class BaseViewModel<STATE : Any, SIDE_EFFECT : Any> : ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {

    override val container: Container<STATE, SIDE_EFFECT> by lazy {
        container(createInitialState()) {
            initData()
        }
    }

    abstract fun createInitialState(): STATE

    open fun initData() = intent { }

    fun uiState(): StateFlow<STATE> = container.stateFlow

    fun uiSideEffect(): Flow<SIDE_EFFECT> = container.sideEffectFlow
}