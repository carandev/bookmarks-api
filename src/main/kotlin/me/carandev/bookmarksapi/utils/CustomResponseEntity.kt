package me.carandev.bookmarksapi.utils

/**
 * Esta clase es una respuesta personalizada que se envía al cliente.
 * @property data Datos que se envían al cliente.
 * @property success Indica si la operación fue exitosa.
 * @property message Mensaje que se envía al cliente.
 */
class CustomResponseEntity<T>(val data: T? = null, val success: Boolean, val message: String? = null)