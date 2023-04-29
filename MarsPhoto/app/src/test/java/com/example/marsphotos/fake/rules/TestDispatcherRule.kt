package com.example.marsphotos.fake.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/*
 we're using a Test Rule here to avoid repeating ourselves
 A JUnit test rule allows us to control the environment
 We're using it here to replace the Main dispatcher with a TestDispatcher for all test cases
 other things you can do are:
    Add additional checks
    Perform setup/cleanup for tests
    Observe and report test execution
*/
class TestDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    // There can be different test dispatchers used
    // UnconfinedTestDispatcher inherits from the base class,
    // UTD is good for simple tests, does not specify an order
    // StandardTestDispatcher allows ordering
): TestWatcher() {
    // starting will execute before a test
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}