package com.example.marvelapp.mock.presentation.heroes

import androidx.paging.PagingData
import com.example.domain.entities.heroes.Hero
import com.example.domain.usecase.GetHeroesUseCase

data class HeroesViewModelMock(
    val params: GetHeroesUseCase.Params = GetHeroesUseCase.Params(""),
    val hero: Hero = Hero(
        name = "Spider-Man",
        imagerUrl = ""
    ),
    val listOfHeroes: List<Hero> = listOf(
        hero,
        hero.copy(name = "Super-Man"),
        hero.copy(name = "3D-Man"),
    ),
    val pagingDataWithHeroes: PagingData<Hero> = PagingData.from(listOfHeroes)
)