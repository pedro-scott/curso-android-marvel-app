package com.example.domain.mock.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.entities.heroes.Hero
import com.example.domain.usecase.GetHeroesUseCase

data class GetHeroesUseCaseImplMock(
    val params: GetHeroesUseCase.Params = GetHeroesUseCase.Params(""),
    val currentPage: Int = 0,
    val nextPage: Int = 20,
    val hero: Hero = Hero(
        name = "Spider-Man",
        imagerUrl = ""
    ),
    val listOfHeroes: List<Hero> = listOf(
        hero,
        hero.copy(name = "Super-Man"),
        hero.copy(name = "3D-Man"),
    ),
    val pagingSource: PagingSource<Int, Hero> = object : PagingSource<Int, Hero>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> =
            LoadResult.Page(
                data = listOfHeroes,
                prevKey = null,
                nextKey = nextPage
            )

        override fun getRefreshKey(state: PagingState<Int, Hero>): Int = currentPage
    }
)