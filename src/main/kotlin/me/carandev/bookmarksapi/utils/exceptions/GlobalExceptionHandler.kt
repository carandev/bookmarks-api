package me.carandev.bookmarksapi.utils.exceptions

import me.carandev.bookmarksapi.utils.CustomResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Clase que maneja las excepciones globales de la aplicación.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * Maneja las excepciones genéricas.
     * @param ex Excepción a manejar.
     * @return ResponseEntity con el mensaje de error.
     */
    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<CustomResponseEntity<String>> {
        val customResponse = CustomResponseEntity<String>(
            data = null,
            false,
            message = "Error: ${ex.message}"
        )
        return ResponseEntity(customResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    /**
     * Maneja las excepciones de tipo NotFoundException.
     * @param ex Excepción a manejar.
     * @return ResponseEntity con el mensaje de error.
     */
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<CustomResponseEntity<String>> {
        val customResponse = CustomResponseEntity<String>(
            data = null,
            false,
            message = ex.message
        )
        return ResponseEntity(customResponse, HttpStatus.NOT_FOUND)
    }

    /**
     * Maneja las excepciones de tipo ConflictException.
     * @param ex Excepción a manejar.
     * @return ResponseEntity con el mensaje de error.
     */
    @ExceptionHandler(ConflictException::class)
    fun handleConflictException(ex: ConflictException): ResponseEntity<CustomResponseEntity<String>> {
        val customResponse = CustomResponseEntity<String>(
            data = null,
            false,
            message = ex.message
        )
        return ResponseEntity(customResponse, HttpStatus.CONFLICT)
    }

    /**
     * Maneja las excepciones de tipo BadRequestException.
     * @param ex Excepción a manejar.
     * @return ResponseEntity con el mensaje de error.
     */
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException): ResponseEntity<CustomResponseEntity<String>> {
        val customResponse = CustomResponseEntity<String>(
            data = null,
            false,
            message = ex.message
        )
        return ResponseEntity(customResponse, HttpStatus.BAD_REQUEST)
    }
}