package com.example.data.paging

import com.example.basetest.base.BaseCoroutineTest
import com.example.data.datasource.CharactersDataSource
import com.example.data.mock.paging.HeroesPagingSourceMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@ExtendWith(MockitoExtension::class)
internal class HeroesPagingSourceTest : BaseCoroutineTest() {

    val mock by lazy { HeroesPagingSourceMock() }

    @Mock
    lateinit var dataSource: CharactersDataSource

    lateinit var pagingSource: HeroesPagingSource

    @BeforeEach
    override fun setup() {
        pagingSource = HeroesPagingSource(
            source = dataSource,
            query = ""
        )
    }

    @AfterEach
    override fun tearDown() = Unit

    @Nested
    @DisplayName("GIVEN initial load from paging")
    inner class InitialLoadFromPaging {

        @Test
        @DisplayName("WHEN load is called and get characters succeed THEN should return load result page")
        fun testSuccess() = testScope.runTest {
            // Arrange
            whenever(dataSource.getCharacters(any()))
                .thenReturn(mock.refreshWrapperResponse)

            // Act
            val result = pagingSource.load(mock.refreshParams)

            // Assert
            assertEquals(
                mock.pageFromRefresh,
                result
            )
        }

        @Test
        @DisplayName("WHEN load is called and get characters fails THEN should return load result error")
        fun testError() = testScope.runTest {
            // Arrange
            whenever(dataSource.getCharacters(any()))
                .thenThrow(mock.exception)

            // Act
            val result = pagingSource.load(mock.refreshParams)

            // Assert
            assertEquals(
                mock.errorFromRefresh,
                result
            )
        }
    }
}