package com.example.marvelapp.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<Intent, State>(
    initialStateValue: State
) : ViewModel() {

    @Suppress("VariableNaming")
    protected val _state: MutableStateFlow<State> = MutableStateFlow(initialStateValue)
    val state: StateFlow<State> = _state.asStateFlow()

    abstract fun handle(intent: Intent)
}