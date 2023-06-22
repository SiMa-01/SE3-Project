package com.example.se3_app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.se3_app.startView.StartViewContent
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.se3_app.launchapp_se3App
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest

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

        val splashView= composeTestRule.onNodeWithTag("splashView")
        composeTestRule.waitUntil(8000) { true }
        splashView.assertIsDisplayed()


        /*    var wait = false
            GlobalScope.launch {
                delay(4000)
                wait = true
            }
            composeTestRule.waitUntil(8000) { wait }
            val view = composeTestRule.onNodeWithTag("startView")
            view.assertIsDisplayed()*/

        /*    composeTestRule.waitForIdle() // Warte auf den Ruhezustand des Compose-Frameworks

            composeTestRule.onNodeWithTag("startView").assertExists()*/

        //val view = composeTestRule.onNodeWithTag("startView")
        //view.assertIsDisplayed()

        /*    var viewExists = false

            while (!viewExists) {
                composeTestRule.waitForIdle()
                try {
                    composeTestRule.onRoot().printToLog("bin im try")
                    val view = composeTestRule.onNodeWithTag("startView")
                    view.assertExists()
                    viewExists = true
                } catch (e: AssertionError) {
                }
            } */


        //     composeTestRule.onRoot().printToLog("mach was")

        /* runBlocking {
            delay(10000)


            composeTestRule.onRoot().printToLog("immer noch was")

            val startScreen = composeTestRule.onNodeWithTag("startView")
            startScreen.assertExists()

        }
    }*/
    }
}