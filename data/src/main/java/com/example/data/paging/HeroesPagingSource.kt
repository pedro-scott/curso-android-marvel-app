package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.datasource.CharactersDataSource
import com.example.domain.entities.heroes.Hero

class HeroesPagingSource(
    private val source: CharactersDataSource,
    private val query: String
) : PagingSource<Int, Hero>() {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> =
        try {
            val queries = hashMapOf(
                Pair(MARVEL_API_OFFSET_QUERY, "${params.key ?: INITIAL_OFFSET}")
            ).also { map ->
                if (query.isNotEmpty())
                    map[MARVEL_API_FILTER_NAME_QUERY] = query
            }

            source.getCharacters(queries).run {
                LoadResult.Page(
                    data = data.results.map { it.toHero() },
                    prevKey = null,
                    nextKey = if (data.offset < data.total) {
                        data.offset + LIMIT_PER_PAGE
                    } else null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT_PER_PAGE) ?: anchorPage?.nextKey?.minus(LIMIT_PER_PAGE)
        }

    companion object {
        // REQUEST QUERIES
        private const val MARVEL_API_OFFSET_QUERY = "offset"
        private const val MARVEL_API_FILTER_NAME_QUERY = "nameStartsWith"

        // PAGING
        const val INITIAL_OFFSET = 0
        const val LIMIT_PER_PAGE = 20
    }
}