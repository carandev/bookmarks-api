package me.carandev.bookmarksapi.utils.exceptions

/**
 * Excepción que se lanza cuando se intenta crear un recurso que ya existe o no coincide algún dato.
 * @param message Mensaje de error.
 */
class ConflictException(message : String) : RuntimeException(message)