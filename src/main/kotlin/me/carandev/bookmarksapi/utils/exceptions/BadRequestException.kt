package me.carandev.bookmarksapi.utils.exceptions

/**
 * Excepción que se lanza cuando se intenta realizar una petición incorrecta.
 * @param message Mensaje de error.
 */
class BadRequestException(message : String) : RuntimeException(message)