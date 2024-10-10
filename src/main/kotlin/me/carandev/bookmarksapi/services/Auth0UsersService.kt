package me.carandev.bookmarksapi.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class Auth0UsersService(private val webClient: WebClient) {

    @Value("\${auth0.api.token}")
    lateinit var apiToken: String

    @Value("\${auth0.domain}")
    lateinit var domain: String

    fun createUser(email: String, password: String, connection: String = "Username-Password-Authentication"): String {
        val requestBody = mapOf(
            "email" to email,
            "password" to password,
            "connection" to connection
        )

        val response = webClient.post()
            .uri("https://$domain/api/v2/users")
            .header("Authorization", "Bearer $apiToken")
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Map::class.java)
            .block()

        return response?.get("user_id") as String
    }
}
