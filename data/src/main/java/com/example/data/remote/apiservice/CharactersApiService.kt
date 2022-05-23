package com.example.data.remote.apiservice

import com.example.data.remote.model.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CharactersApiService {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse
}