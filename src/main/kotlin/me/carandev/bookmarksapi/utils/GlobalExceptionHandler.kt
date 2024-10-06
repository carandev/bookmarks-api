package me.carandev.bookmarksapi.utils

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<CustomResponseEntity<String>> {
        val customResponse = CustomResponseEntity<String>(
            data = null,
            message = "Error: ${ex.message}"
        )
        return ResponseEntity(customResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}