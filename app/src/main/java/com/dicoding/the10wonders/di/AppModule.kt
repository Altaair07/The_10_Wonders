package com.dicoding.the10wonders.di

import com.dicoding.the10wonders.data.repository.WonderRepositoryImpl
import com.dicoding.the10wonders.domain.repository.WonderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideWonderRepository(wonderRepositoryImpl: WonderRepositoryImpl): WonderRepository
}