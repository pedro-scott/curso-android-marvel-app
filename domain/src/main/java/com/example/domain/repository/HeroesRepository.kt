package com.example.domain.repository

import androidx.paging.PagingSource
import com.example.domain.entities.heroes.Hero

interface HeroesRepository {
    fun getHeroes(query: String): PagingSource<Int, Hero>
}