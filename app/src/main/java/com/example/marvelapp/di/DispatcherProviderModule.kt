package com.example.marvelapp.di

import com.example.marvelapp.util.dispatcher.DefaultDispatcherProvider
import com.example.marvelapp.util.dispatcher.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface DispatcherProviderModule {

    @Binds
    @ViewModelScoped
    fun bindDispatcherProvider(provider: DefaultDispatcherProvider): DispatcherProvider
}