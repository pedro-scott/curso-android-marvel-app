package com.example.domain.usecase

import com.example.basetest.base.BaseCoroutineTest
import com.example.domain.mock.usecase.GetHeroesUseCaseImplMock
import com.example.domain.repository.HeroesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@ExtendWith(MockitoExtension::class)
internal class GetHeroesUseCaseImplTest : BaseCoroutineTest() {

    val mock by lazy { GetHeroesUseCaseImplMock() }

    @Mock
    lateinit var repository: HeroesRepository

    lateinit var useCase: GetHeroesUseCase

    @BeforeEach
    override fun setup() {
        useCase = GetHeroesUseCaseImpl(repository)
    }

    @AfterEach
    override fun tearDown() = Unit

    @Test
    @DisplayName("GIVEN GetHeroesUseCaseImpl is instantiated WHEN invoke is called THEN should get the paging source from repository")
    fun testUseCaseInvoke() = testScope.runTest {
        // Arrange
        whenever(repository.getHeroes(""))
            .thenReturn(mock.pagingSource)

        // Act
        val result = useCase(mock.params)

        // Assert
        verify(repository).getHeroes("")
        checkNotNull(result.first())
    }
}