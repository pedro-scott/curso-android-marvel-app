package com.example.marvelapp.di

import com.example.data.repository.HeroesRepositoryImpl
import com.example.domain.repository.HeroesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun bindHeroesRepository(repository: HeroesRepositoryImpl): HeroesRepository
}