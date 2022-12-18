package com.ozcan.alasalvar.solarsystemapp.di

import com.ozcan.alasalvar.solarsystemapp.data.repository.RepositoryImpl
import com.ozcan.alasalvar.solarsystemapp.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun bindRepository(repository: RepositoryImpl): Repository
}