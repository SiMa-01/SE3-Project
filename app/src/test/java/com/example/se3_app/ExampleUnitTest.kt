package com.example.se3_app

import com.example.se3_app.api.ApiManager
import com.example.se3_app.service.*
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Test API Anfrage
 */
class ExampleUnitTest {

    private lateinit var cocktailService: CocktailService
    private val apiManager: ApiManager = mockk(relaxed = true)

    @Before
    fun setup() {
        cocktailService = CocktailService()
        cocktailService.apiManager = apiManager
    }

    @Test
    fun findCocktails() = runBlocking {
        // Arrange
        val cocktailService = CocktailService()

        // Act
        val cocktailsMojitoName = cocktailService.findCocktails(name = "Mojito")
        // Assert
        assertEquals(1, cocktailsMojitoName.size)
        assertEquals("Mojito", cocktailsMojitoName[0].name)
        assertTrue(cocktailsMojitoName[0].alcoholic)
    }
}
