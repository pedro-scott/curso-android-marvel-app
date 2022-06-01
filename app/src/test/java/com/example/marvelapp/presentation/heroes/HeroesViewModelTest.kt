package com.example.marvelapp.presentation.heroes

import com.example.domain.usecase.GetHeroesUseCase
import com.example.basetest.base.BaseCoroutineTest
import com.example.marvelapp.mock.presentation.heroes.HeroesViewModelMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
internal class HeroesViewModelTest : BaseCoroutineTest() {

    val mock by lazy { HeroesViewModelMock() }

    @Mock
    lateinit var useCase: GetHeroesUseCase

    lateinit var viewModel: HeroesViewModel

    @BeforeEach
    override fun setup() {
        viewModel = HeroesViewModel(useCase)
    }

    @AfterEach
    override fun tearDown() = Unit

    @Test
    @DisplayName("GIVEN heroes fragment is created WHEN load heroes is called THEN should load the initial list of heroes")
    fun testInitialLoadFromHeroesList() = testScope.runTest() {
        // Arrange
        whenever(useCase(mock.params))
            .thenReturn(flowOf(mock.pagingDataWithHeroes))

        // Act
        viewModel.handle(HeroesIntent.LoadHeroes(""))

        // Assert
        assertInstanceOf(
            HeroesState.ShowHeroes::class.java,
            viewModel.state.value
        )

        (viewModel.state.value as? HeroesState.ShowHeroes)?.run {
            assertNotNull(heroesFlow.first())
        }
    }

    @Nested
    @DisplayName("GIVEN refresh list of heroes")
    inner class RefreshListOfHeroes {

        @Test
        @DisplayName("WHEN is loading THEN should show loading")
        fun testLoading() = testScope.runTest() {
            // Act
            viewModel.handle(HeroesIntent.RefreshLoading(true))

            // Assert
            assertEquals(
                viewModel.state.value,
                HeroesState.HeroesLoading(true)
            )
        }

        @Test
        @DisplayName("WHEN is not loading and succeed THEN should show list of heroes")
        fun testSuccess() = testScope.runTest() {
            // Act
            viewModel.handle(HeroesIntent.RefreshLoading(false))

            // Assert
            assertEquals(
                viewModel.state.value,
                HeroesState.HeroesLoading(false)
            )
        }

        @Test
        @DisplayName("WHEN is not loading and an error occurs THEN should show error layout")
        fun testError() = testScope.runTest() {
            // Act
            viewModel.handle(HeroesIntent.RefreshError)

            // Assert
            assertEquals(
                viewModel.state.value,
                HeroesState.HeroesError
            )
        }
    }

    @Test
    @DisplayName("GIVEN error layout is visible WHEN click to try again THEN should try to refresh list of heroes")
    fun testRefreshListAfterError() = testScope.runTest() {
        // Act
        viewModel.handle(HeroesIntent.RefreshList)

        // Assert
        assertEquals(
            viewModel.state.value,
            HeroesState.HeroesRefresh
        )
    }
}