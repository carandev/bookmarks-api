package me.carandev.bookmarksapi.models.dtos.requests.users

import me.carandev.bookmarksapi.models.entities.User

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
    fun toUser(auth0Id: String) = User(name = name, email = email, auth0Id = auth0Id)
}
