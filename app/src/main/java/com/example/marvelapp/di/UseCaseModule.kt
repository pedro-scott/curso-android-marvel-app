package com.example.marvelapp.di

import com.example.domain.usecase.GetHeroesUseCase
import com.example.domain.usecase.GetHeroesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    @ViewModelScoped
    fun bindGetHeroesUseCase(useCase: GetHeroesUseCaseImpl): GetHeroesUseCase
}