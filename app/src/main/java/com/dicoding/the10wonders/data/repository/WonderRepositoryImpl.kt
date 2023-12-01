package com.dicoding.the10wonders.data.repository

import com.dicoding.the10wonders.domain.model.DataWonder
import com.dicoding.the10wonders.domain.model.Wonder
import com.dicoding.the10wonders.domain.repository.WonderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WonderRepositoryImpl @Inject constructor() : WonderRepository {

    private val wonders = DataWonder.wonders

    override fun getWonders(): Flow<List<Wonder>> {
        return flowOf(wonders)
    }

    override fun getDetailWonder(id: Int): Flow<Wonder?> = flow {
        val wonder = wonders.find {
            it.id == id
        }
        emit(wonder)
    }.flowOn(Dispatchers.IO)
}