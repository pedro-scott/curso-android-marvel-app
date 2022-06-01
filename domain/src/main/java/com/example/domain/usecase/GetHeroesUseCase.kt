package com.example.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.domain.base.BaseUseCase
import com.example.domain.entities.heroes.Hero
import com.example.domain.repository.HeroesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetHeroesUseCase : BaseUseCase<GetHeroesUseCase.Params, PagingData<Hero>> {
    data class Params(val query: String)
}

class GetHeroesUseCaseImpl @Inject constructor(
    private val repository: HeroesRepository
) : GetHeroesUseCase {

    override fun invoke(params: GetHeroesUseCase.Params): Flow<PagingData<Hero>> =
        repository.getHeroes(params.query).let { pagingSource ->
            Pager(
                config = PagingConfig(pageSize = PAGE_SIZE)
            ) { pagingSource }.flow
        }

    companion object {
        // PAGING
        private const val PAGE_SIZE = 20
    }
}