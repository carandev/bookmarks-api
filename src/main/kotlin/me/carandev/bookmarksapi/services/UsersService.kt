package me.carandev.bookmarksapi.services

import me.carandev.bookmarksapi.repositories.UsersRepository
import me.carandev.bookmarksapi.models.dtos.requests.users.CreateUserRequest
import me.carandev.bookmarksapi.models.dtos.requests.users.UpdateUserRequest
import me.carandev.bookmarksapi.models.dtos.responses.UserResponse
import me.carandev.bookmarksapi.utils.exceptions.ConflictException
import me.carandev.bookmarksapi.utils.exceptions.NotFoundException
import org.springframework.stereotype.Service

/**
 * Este es el servicio para los usuarios.
 * @property repository Repositorio de los usuarios.
 * @constructor Crea un servicio para los usuarios.
 */
@Service
class UsersService(val repository: UsersRepository) {

    /**
     * Crea un usuario.
     * @param userRequest Solicitud para crear un usuario.
     * @return La respuesta del usuario creado.
     * @throws ConflictException Si el usuario ya existe.
     */
    fun create(userRequest: CreateUserRequest): UserResponse {

        if (repository.existsUserByEmail(userRequest.email)) {
            throw ConflictException("El usuario con el correo ${userRequest.email} ya está registrado.")
        }

        val createdUser = repository.save(userRequest.toUser())

        return UserResponse(createdUser.id, createdUser.name, createdUser.email)
    }

    /**
     * Lista los usuarios.
     * @return La lista de usuarios.
     */
    fun list(): List<UserResponse> =
        repository.findAllUsers().map { UserResponse(it.getId(), it.getName(), it.getEmail()) }

    /**
     * Encuentra un usuario por su identificador.
     * @param id Identificador del usuario.
     * @return La respuesta del usuario encontrado.
     * @throws NotFoundException Si el usuario no se encuentra.
     */
    fun findById(id: Long): UserResponse {
        val userProjection = repository.findUserById(id)

        if (userProjection != null) {
            return UserResponse(userProjection.getId(), userProjection.getName(), userProjection.getEmail())
        }

        throw NotFoundException("Usuario no encontrado")
    }

    /**
     * Actualiza un usuario por su identificador.
     * @param id Identificador del usuario.
     * @param userRequest Datos para actualizar el usuario.
     * @return La respuesta del usuario actualizado.
     * @throws NotFoundException Si el usuario no se encuentra.
     */
    fun updateById(id: Long, userRequest: UpdateUserRequest): UserResponse {
        val userToUpdate = repository.findById(id)

        if (userToUpdate.isPresent) {
            val updatedUser = userToUpdate.get().copy(name = userRequest.name)
            repository.save(updatedUser)

            return UserResponse(updatedUser.id, updatedUser.name, updatedUser.email)
        }

        throw NotFoundException("Usuario no encontrado")
    }

    /**
     * Elimina un usuario por su identificador.
     * @param id Identificador del usuario.
     * @return La respuesta del usuario eliminado.
     * @throws NotFoundException Si el usuario no se encuentra.
     */
    fun deleteUserById(id: Long): UserResponse {
        val user = repository.findById(id)

        if (user.isPresent) {
            val userToDelete = user.get()
            val deletedUser = UserResponse(userToDelete.id, userToDelete.name, userToDelete.email)
            repository.deleteById(id)
            return deletedUser
        }

        throw NotFoundException("Usuario no encontrado")
    }
}