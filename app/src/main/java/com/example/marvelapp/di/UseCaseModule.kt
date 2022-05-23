package com.example.marvelapp.di

import androidx.paging.PagingData
import com.example.domain.base.BaseUseCase
import com.example.domain.entities.heroes.Hero
import com.example.domain.usecase.GetHeroesUseCase
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
    fun bindGetHeroesUseCase(useCase: GetHeroesUseCase): BaseUseCase<GetHeroesUseCase.Params, PagingData<Hero>>
}