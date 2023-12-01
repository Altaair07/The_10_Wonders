package com.dicoding.the10wonders.presentation.screen.home

import com.dicoding.the10wonders.domain.model.Wonder

data class HomeState(
    val wonders: List<Wonder> = emptyList(),
)