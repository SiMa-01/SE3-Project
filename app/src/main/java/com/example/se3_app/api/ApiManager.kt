package com.example.se3_app.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.serialization.json.Json

class ApiManager {
    var httpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }
            )
        }
        defaultRequest {
            url.protocol = URLProtocol.HTTPS
            url.host = "cocktail-service.onrender.com"
            url.encodedPath = "/api" + url.encodedPath
            contentType(ContentType.Application.Json)
        }
        HttpResponseValidator {
            handleResponseException { exception ->
                var exceptionResponseText =
                    exception.message ?: "Unkown Error occurred. Please contact your administrator."
                if (exception is ClientRequestException) {
                    // 400 errors
                    val exceptionResponse = exception.response
                    exceptionResponseText = exceptionResponse.toString()
                } else if (exception is ServerResponseException) {
                    // 500 errors
                    val exceptionResponse = exception.response
                    exceptionResponseText = exceptionResponse.toString()
                }
                throw CancellationException(exceptionResponseText)
            }
        }
    }
}
