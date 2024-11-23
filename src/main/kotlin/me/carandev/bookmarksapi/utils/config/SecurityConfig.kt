package me.carandev.bookmarksapi.utils.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests { authorize ->
                authorize
                    .anyRequest().authenticated()
            }
            .cors(withDefaults())
            .oauth2ResourceServer { oauth2 ->
                oauth2
                    .jwt(withDefaults())
            }
            .build();
    }

    // Configuración global de CORS
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:5173") // Origen permitido
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE") // Métodos permitidos
        configuration.allowedHeaders = listOf("Authorization", "Content-Type") // Encabezados permitidos

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}