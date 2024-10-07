package me.carandev.bookmarksapi.dtos.requests.users

import me.carandev.bookmarksapi.entities.User

/**
 * Este es el archivo de la solicitud para crear un usuario.
 * @property name Nombre del usuario.
 * @property email Correo electrónico del usuario.
 * @constructor Crea una solicitud para crear un usuario.
 */
data class CreateUserRequest(val name: String, val email: String) {

    /**
     * Convierte la solicitud en un usuario.
     * @return El usuario creado.
     */
    fun toUser() = User(name = name, email = email)
}
