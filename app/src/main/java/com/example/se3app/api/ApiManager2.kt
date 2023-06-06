package com.example.se3app.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

class ApiManager2 {
    var httpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
        defaultRequest {
            url.protocol = URLProtocol.HTTPS
            url.host = "warenkorb-api.onrender.com"
            url.encodedPath = "/api" + url.encodedPath
            contentType(ContentType.Application.Json)
        }
        HttpResponseValidator {
            handleResponseException { exception ->
                var exceptionResponseText =
                    exception.message ?: "Unkown Error occurred. Please contact your administrator."
                if (exception is ClientRequestException) {
                    //400 errors
                    val exceptionResponse = exception.response
                    exceptionResponseText = exceptionResponse.toString()
                } else if (exception is ServerResponseException) {
                    //500 errors
                    val exceptionResponse = exception.response
                    exceptionResponseText = exceptionResponse.toString()
                }
                throw CancellationException(exceptionResponseText)
            }
        }
    }
}