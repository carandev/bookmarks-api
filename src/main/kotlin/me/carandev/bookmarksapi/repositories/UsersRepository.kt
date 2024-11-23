package me.carandev.bookmarksapi.repositories

import me.carandev.bookmarksapi.models.dtos.projections.IUserProjection
import me.carandev.bookmarksapi.models.entities.User
import me.carandev.bookmarksapi.models.dtos.responses.UserResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * Repositorio de usuarios.
 */
@Repository
interface UsersRepository : JpaRepository<User, Long> {

    /**
     * Lista todos los usuarios y hace la proyección al DTO.
     * @return Lista de usuarios.
     */
    @Query("SELECT u.id AS id, u.name AS name, u.email AS email FROM User u")
    fun findAllUsers() : List<IUserProjection>

    /**
     * Busca un usuario por su identificador y hace la proyección al DTO.
     * @param id Identificador del usuario.
     * @return Usuario encontrado.
     */
    @Query("SELECT u.id AS id, u.name AS name, u.email AS email FROM User u WHERE u.id = :id")
    fun findUserById(id: Long) : IUserProjection?

    /**
     * Busca un usuario por su identificador de Auth0 y hace la proyección al DTO.
     * @param auth0Id Identificador de Auth0 del usuario.
     * @return Usuario encontrado.
     */
    fun findUserByAuth0Id(auth0Id: String) : IUserProjection?

    /**
     * Verifica si existe un usuario por su correo electrónico.
     * @param email Correo electrónico del usuario.
     * @return Si existe o no el usuario.
     */
    fun existsUserByEmail(email: String) : Boolean
}