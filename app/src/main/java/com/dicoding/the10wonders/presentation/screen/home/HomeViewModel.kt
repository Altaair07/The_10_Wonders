package com.dicoding.the10wonders.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.the10wonders.domain.repository.WonderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val wonderRepository: WonderRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getWonders()
    }

    private fun getWonders() = viewModelScope.launch {
        wonderRepository.getWonders().collect { result ->
            _state.update {
                it.copy(
                    wonders = result
                )
            }
        }
    }
}