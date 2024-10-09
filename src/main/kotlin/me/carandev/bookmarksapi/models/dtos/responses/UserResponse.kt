package me.carandev.bookmarksapi.models.dtos.responses

/**
 * Este es el archivo de la respuesta de un usuario.
 * @property id Identificador del usuario.
 * @property name Nombre del usuario.
 * @property email Correo electrónico del usuario.
 */
data class UserResponse(val id : Long, val name : String, val email : String)
