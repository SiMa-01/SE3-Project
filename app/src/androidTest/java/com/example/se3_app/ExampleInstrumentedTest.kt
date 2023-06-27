package com.example.se3_app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.launchapp_se3App(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun appLaunches() {
        val loginView = composeTestRule.onNodeWithTag("loginView")
        loginView.assertExists()

        val emailField = composeTestRule.onNodeWithTag("emailField")
        emailField.performTextInput("mayer-simon@web.de")
        var wait = false
        GlobalScope.launch {
            delay(4000)
            wait = true
        }
        composeTestRule.waitUntil(8000) { wait }

        val passwordField = composeTestRule.onNodeWithTag("passwordField")
        passwordField.performTextInput("test1234")
        var wait2 = false
        GlobalScope.launch {
            delay(4000)
            wait2 = true
        }
        composeTestRule.waitUntil(8000) { wait2 }

        val loginButton = composeTestRule.onNodeWithTag("loginButton")
        loginButton.performClick()

        var wait3 = false
        GlobalScope.launch {
            delay(4000)
            wait3 = true
        }
        composeTestRule.waitUntil(8000) { wait3 }

        val splashView = composeTestRule.onNodeWithTag("splashView")
        composeTestRule.waitUntil(8000) { true }
        splashView.assertIsDisplayed()
    }

    @Test
    fun searchCocktail() {
        val loginView = composeTestRule.onNodeWithTag("loginView")
        loginView.assertExists()

        val emailField = composeTestRule.onNodeWithTag("emailField")
        emailField.performTextInput("mayer-simon@web.de")
        var wait = false
        GlobalScope.launch {
            delay(4000)
            wait = true
        }
        composeTestRule.waitUntil(8000) { wait }

        val passwordField = composeTestRule.onNodeWithTag("passwordField")
        passwordField.performTextInput("test1234")
        var wait2 = false
        GlobalScope.launch {
            delay(4000)
            wait2 = true
        }
        composeTestRule.waitUntil(8000) { wait2 }

        val loginButton = composeTestRule.onNodeWithTag("loginButton")
        loginButton.performClick()

        var wait3 = false
        GlobalScope.launch {
            delay(4000)
            wait3 = true
        }
        composeTestRule.waitUntil(8000) { wait3 }

        val splashView = composeTestRule.onNodeWithTag("splashView")
        composeTestRule.waitUntil(8000) { true }
        splashView.assertIsDisplayed()

        var wait4 = false
        GlobalScope.launch {
            delay(10000)
            wait4 = true
        }
        composeTestRule.waitUntil(15000) { wait4 }

        val searchCocktailButton = composeTestRule.onNodeWithTag("searchCocktailButton")
        searchCocktailButton.performClick()
        var wait6 = false
        GlobalScope.launch {
            delay(4000)
            wait6 = true
        }
        composeTestRule.waitUntil(8000) { wait6 }

        val searchCocktailView = composeTestRule.onNodeWithTag("searchCocktailView")
        searchCocktailView.assertIsDisplayed()

        var wait8 = false
        GlobalScope.launch {
            delay(4000)
            wait8 = true
        }
        composeTestRule.waitUntil(8000) { wait8 }

        val searchButton = composeTestRule.onNodeWithTag("searchButton")
        searchButton.performClick()

        var wait7 = false
        GlobalScope.launch {
            delay(10000)
            wait7 = true
        }
        composeTestRule.waitUntil(15000) { wait7 }

        val resultView = composeTestRule.onNodeWithTag("resultView")
        resultView.assertIsDisplayed()
    }
}
