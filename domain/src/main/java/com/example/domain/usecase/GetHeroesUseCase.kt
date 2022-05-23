package com.example.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.domain.base.BaseUseCase
import com.example.domain.entities.heroes.Hero
import com.example.domain.repository.HeroesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHeroesUseCase @Inject constructor(
    private val repository: HeroesRepository
) : BaseUseCase<GetHeroesUseCase.Params, PagingData<Hero>> {

    override fun invoke(params: Params): Flow<PagingData<Hero>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE)
        ) { repository.getHeroes(params.query) }.flow

    data class Params(val query: String)

    companion object {
        // PAGING
        private const val PAGE_SIZE = 20
    }
}