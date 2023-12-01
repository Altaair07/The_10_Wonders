package com.dicoding.the10wonders.presentation.screen.detail

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
class DetailViewModel @Inject constructor(
    private val wonderRepository: WonderRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnGetDetail -> getDetailWonder(event.id)
        }
    }

    private fun getDetailWonder(id: Int) = viewModelScope.launch {
        wonderRepository.getDetailWonder(id).collect { wonder ->
            _state.update {
                it.copy(
                    wonder = wonder,
                )
            }
        }
    }
}