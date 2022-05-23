package com.example.domain.base

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in P, out R> {

    operator fun invoke(params: P): Flow<R>
}