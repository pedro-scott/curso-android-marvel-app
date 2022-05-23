package com.example.data.repository

import androidx.paging.PagingSource
import com.example.data.datasource.CharactersDataSource
import com.example.data.paging.HeroesPagingSource
import com.example.domain.entities.heroes.Hero
import com.example.domain.repository.HeroesRepository
import javax.inject.Inject

class HeroesRepositoryImpl @Inject constructor(
    private val source: CharactersDataSource
) : HeroesRepository {

    override fun getHeroes(query: String): PagingSource<Int, Hero> =
        HeroesPagingSource(
            source = source,
            query = query
        )
}