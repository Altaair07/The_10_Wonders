package com.dicoding.the10wonders.presentation.screen.detail

sealed class DetailEvent {
    data class OnGetDetail(
        val id: Int
    ) : DetailEvent()
}