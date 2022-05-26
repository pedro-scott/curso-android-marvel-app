package com.example.marvelapp.presentation.heroes

sealed class HeroesIntent {
    object RefreshList : HeroesIntent()
    object RefreshError : HeroesIntent()
    data class LoadHeroes(val query: String) : HeroesIntent()
    data class RefreshLoading(val isLoading: Boolean) : HeroesIntent()
}