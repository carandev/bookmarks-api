package me.carandev.bookmarksapi.utils.exceptions

/**
 * Excepción que se lanza cuando no se encuentra un recurso.
 * @param message Mensaje de error.
 */
class NotFoundException(message : String) : RuntimeException(message)