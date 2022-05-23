package com.example.marvelapp.presentation.heroes

sealed class HeroesIntent {
    data class LoadHeroes(val query: String) : HeroesIntent()
}