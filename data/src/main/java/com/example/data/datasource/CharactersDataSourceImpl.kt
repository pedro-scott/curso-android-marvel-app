package com.example.data.datasource

import com.example.data.remote.apiservice.CharactersApiService
import com.example.data.remote.model.response.DataWrapperResponse
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

interface CharactersDataSource {
    suspend fun getCharacters(queries: Map<String, String>): DataWrapperResponse
}

class CharactersDataSourceImpl @Inject constructor(retrofit: Retrofit) : CharactersDataSource {

    private val service by lazy {
        retrofit.create<CharactersApiService>()
    }

    override suspend fun getCharacters(queries: Map<String, String>): DataWrapperResponse {
        return service.getCharacters(queries)
    }
}