package me.carandev.bookmarksapi.repositories

import me.carandev.bookmarksapi.entities.User
import me.carandev.bookmarksapi.dtos.responses.UserResponse
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
    @Query("SELECT new me.carandev.bookmarksapi.dtos.responses.UserResponse(u.id, u.name, u.email) FROM User u")
    fun findAllUsers() : List<UserResponse>

    /**
     * Busca un usuario por su identificador y hace la proyección al DTO.
     * @param id Identificador del usuario.
     * @return Usuario encontrado.
     */
    @Query("SELECT new me.carandev.bookmarksapi.dtos.responses.UserResponse(u.id, u.name, u.email) FROM User u WHERE u.id = :id")
    fun findUserById(id: Long) : UserResponse?

    /**
     * Verifica si existe un usuario por su correo electrónico.
     * @param email Correo electrónico del usuario.
     * @return Si existe o no el usuario.
     */
    fun existsUserByEmail(email: String) : Boolean
}