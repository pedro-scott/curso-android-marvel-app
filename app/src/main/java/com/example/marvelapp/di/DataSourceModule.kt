package com.example.marvelapp.di

import com.example.data.datasource.CharactersDataSource
import com.example.data.datasource.CharactersDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface DataSourceModule {

    @Binds
    @ViewModelScoped
    fun bindCharactersDataSource(source: CharactersDataSourceImpl): CharactersDataSource
}