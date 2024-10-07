package me.carandev.bookmarksapi.controllers

import me.carandev.bookmarksapi.dtos.requests.users.CreateUserRequest
import me.carandev.bookmarksapi.dtos.requests.users.UpdateUserRequest
import me.carandev.bookmarksapi.dtos.responses.UserResponse
import me.carandev.bookmarksapi.services.UsersService
import me.carandev.bookmarksapi.utils.CustomResponseEntity
import me.carandev.bookmarksapi.utils.UrlPaths
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(UrlPaths.API_USERS)
class UsersController(val service: UsersService) {

    /**
     * Endpoint para crear un usuario.
     * @param userRequest Solicitud para crear un usuario.
     * @return La respuesta del usuario creado.
     */
    @PostMapping
    fun createUser(@RequestBody userRequest: CreateUserRequest): ResponseEntity<CustomResponseEntity<UserResponse?>> {
        val userResponse = service.create(userRequest)
        return ResponseEntity(
            CustomResponseEntity(userResponse, true, "Usuario creado con éxito: ${userResponse.id}"),
            HttpStatus.CREATED
        )
    }

    /**
     * Endpoint para listar los usuarios.
     * @return La lista de usuarios.
     */
    @GetMapping
    fun getUsers(): ResponseEntity<CustomResponseEntity<List<UserResponse>>> {
        val users = service.list()

        val message = if (users.isEmpty()) "No se encontraron usuarios" else "Usuarios encontrados"
        return ResponseEntity(CustomResponseEntity(users, true, message), HttpStatus.OK)
    }

    /**
     * Endpoint para encontrar un usuario por su identificador.
     * @param id Identificador del usuario.
     * @return La respuesta del usuario encontrado.
     */
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<CustomResponseEntity<UserResponse?>> {
        val user = service.findById(id)
        return ResponseEntity(CustomResponseEntity(user, true, "Usuario encontrado"), HttpStatus.OK)
    }

    /**
     * Endpoint para actualizar un usuario por su identificador.
     * @param id Identificador del usuario.
     * @param userRequest Datos para actualizar el usuario.
     * @return La respuesta del usuario actualizado.
     */
    @PutMapping("/{id}")
    fun updateUserById(
        @PathVariable id: Long,
        @RequestBody userRequest: UpdateUserRequest
    ): ResponseEntity<CustomResponseEntity<UserResponse?>> {
        val updatedUser = service.updateById(id, userRequest)
        return ResponseEntity(
            CustomResponseEntity(updatedUser, true, "Usuario actualizado"),
            HttpStatus.OK
        )
    }

    /**
     * Endpoint para eliminar un usuario por su identificador.
     * @param id Identificador del usuario.
     * @return La respuesta del usuario eliminado.
     */
    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long): ResponseEntity<CustomResponseEntity<UserResponse?>> {
        val deletedUser = service.deleteUserById(id)
        return ResponseEntity(CustomResponseEntity(deletedUser, true, "Usuario eliminado"), HttpStatus.OK)
    }
}