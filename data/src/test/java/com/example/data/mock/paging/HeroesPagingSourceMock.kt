package com.example.data.mock.paging

import androidx.paging.PagingSource
import com.example.data.paging.HeroesPagingSource
import com.example.data.remote.model.response.CharacterResponse
import com.example.data.remote.model.response.DataContainerResponse
import com.example.data.remote.model.response.DataWrapperResponse
import com.example.data.remote.model.response.ThumbnailResponse
import com.example.domain.entities.heroes.Hero
import kotlin.RuntimeException

data class HeroesPagingSourceMock(
    val exception: RuntimeException = RuntimeException(),
    val thumbnail: ThumbnailResponse =
        ThumbnailResponse(
            path = "http://example.image",
            extension = "jpg"
        ),
    val character: CharacterResponse =
        CharacterResponse(
            id = "0000",
            name = "Spider-Man",
            thumbnail = thumbnail
        ),
    val listCharacters: List<CharacterResponse> = listOf(
        character,
        character.copy(
            id = "0001",
            name = "Super-Man"
        ),
        character.copy(
            id = "0002",
            name = "Batman"
        )
    ),
    val containerResponse: DataContainerResponse =
        DataContainerResponse(
            offset = HeroesPagingSource.INITIAL_OFFSET,
            total = listCharacters.size,
            results = listCharacters
        ),
    val refreshWrapperResponse: DataWrapperResponse =
        DataWrapperResponse(
            copyright = "",
            data = containerResponse
        ),
    val refreshParams: PagingSource.LoadParams.Refresh<Int> =
        PagingSource.LoadParams.Refresh(
            key = null,
            loadSize = HeroesPagingSource.LIMIT_PER_PAGE,
            placeholdersEnabled = false
        ),
    val pageFromRefresh: PagingSource.LoadResult.Page<Int, Hero> =
        PagingSource.LoadResult.Page(
            data = refreshWrapperResponse.data.results.map { it.toHero() },
            prevKey = null,
            nextKey = HeroesPagingSource.INITIAL_OFFSET + HeroesPagingSource.LIMIT_PER_PAGE
        ),
    val errorFromRefresh: PagingSource.LoadResult.Error<Int, Hero> =
        PagingSource.LoadResult.Error(exception)
)