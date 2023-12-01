package com.dicoding.the10wonders.domain.repository

import com.dicoding.the10wonders.domain.model.Wonder
import kotlinx.coroutines.flow.Flow

interface WonderRepository {
    fun getWonders(): Flow<List<Wonder>>
    fun getDetailWonder(id: Int): Flow<Wonder?>
}