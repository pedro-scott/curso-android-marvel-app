package com.example.basetest.base

import com.example.basetest.rule.CoroutineExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import org.mockito.junit.jupiter.MockitoExtension

@ExperimentalCoroutinesApi
@ExtendWith(MockitoExtension::class)
abstract class BaseCoroutineTest {

    protected val testScope = TestScope()
    protected val testDispatcher = UnconfinedTestDispatcher(testScope.testScheduler)

    @RegisterExtension
    protected val coroutineExtension = CoroutineExtension(testDispatcher)

    abstract fun setup()

    abstract fun tearDown()
}