package com.example.se3_app

import com.example.se3_app.Dto.CocktailDto
import com.example.se3_app.api.ApiManager
import com.example.se3_app.service.CocktailService
import io.ktor.client.*
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun findCocktails() {
        // Mocking des HttpClient
        val httpClientMock = mockk<HttpClient>()

        // Mocking des ApiManager
        val apiManagerMock = mockk<ApiManager>() {
            every { httpClient } returns httpClientMock
        }

        // Mocking des CocktailService mit der mock ApiManager Instanz
        val cocktailService = mockk<CocktailService>()

        // Setup für das Stubbing des Ergebnisses der findCocktails Methode
        coEvery {
            cocktailService.findCocktails(any(), any(), any(), any(), any())
        } returns listOf(
            CocktailDto(
                _id = "1",
                name = "Mojito",
                ingredients = arrayOf("Mint", "Sugar", "Lime", "Rum", "Soda"),
                difficulty = "Easy",
                alcoholic = true,
                taste = "Fresh",
                preparation = "Some preparation text"
            )
            // Fügen Sie hier zusätzliche CocktailDto-Instanzen hinzu...
        )

        // Ruft die findCocktails Methode auf und speichert das Ergebnis
        val result = runBlocking {
            cocktailService.findCocktails(name = "Mojito")
        }
        // Assert
        assertEquals(1, result.size)
        assertEquals("Mojito", result[0].name)
        assertTrue(result[0].alcoholic)
    }
}
