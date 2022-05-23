package com.example.marvelapp.presentation.heroes

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.usecase.GetHeroesUseCase
import com.example.marvelapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val getHeroesUseCase: GetHeroesUseCase
) : BaseViewModel<HeroesIntent, HeroesState>(HeroesState.InitialState) {

    override fun handle(intent: HeroesIntent) {
        when (intent) {
            is HeroesIntent.LoadHeroes -> handleLoadHeroes(intent.query)
        }
    }

    private fun handleLoadHeroes(query: String) {
        getHeroesUseCase(GetHeroesUseCase.Params(query))
            .cachedIn(viewModelScope)
            .let { heroesFlow ->
                _state.update { HeroesState.ShowHeroes(heroesFlow) }
            }
    }
}