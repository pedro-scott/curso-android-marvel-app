package com.example.marvelapp.presentation.heroes

import androidx.paging.PagingData
import com.example.domain.entities.heroes.Hero
import kotlinx.coroutines.flow.Flow

sealed class HeroesState {
    object InitialState : HeroesState()
    object HeroesRefresh : HeroesState()
    object HeroesError : HeroesState()
    data class ShowHeroes(val heroesFlow: Flow<PagingData<Hero>>) : HeroesState()
    data class HeroesLoading(val isLoading: Boolean) : HeroesState()
}