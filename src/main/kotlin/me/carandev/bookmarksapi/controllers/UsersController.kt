package me.carandev.bookmarksapi.controllers

import me.carandev.bookmarksapi.models.User
import me.carandev.bookmarksapi.repositories.UsersRepository
import me.carandev.bookmarksapi.utils.CustomResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UsersController(val usersRepository: UsersRepository) {

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<CustomResponseEntity<User?>> {
        val savedUser = usersRepository.save(user)

        val customResponseEntity =
            CustomResponseEntity<User?>(savedUser, "Usuario creado con éxito: ${savedUser.id}")
        return ResponseEntity(customResponseEntity, HttpStatus.CREATED)
    }

    @GetMapping
    fun getUsers(): ResponseEntity<CustomResponseEntity<List<User>>> {
        val users = usersRepository.findAll()

        val message = if (users.isEmpty()) "No se encontraron usuarios" else "Usuarios encontrados"
        val customResponseEntity = CustomResponseEntity<List<User>>(users, message)
        return ResponseEntity(customResponseEntity, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<CustomResponseEntity<User?>> {
        val user = usersRepository.findById(id)

        if (user.isPresent){
            val customResponseEntity = CustomResponseEntity<User?>(user.get(), "Usuario encontrado")
            return ResponseEntity(customResponseEntity, HttpStatus.OK)
        } else {
            val customResponseEntity = CustomResponseEntity<User?>(null, "Usuario no encontrado")
            return ResponseEntity(customResponseEntity, HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updateUserById(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<CustomResponseEntity<User?>> {
        val userToUpdate = usersRepository.findById(id)

        if (userToUpdate.isPresent){
            val updatedUser = userToUpdate.get().copy(name = user.name, email = user.email)
            usersRepository.save(updatedUser)
            val customResponseEntity = CustomResponseEntity<User?>(updatedUser, "Usuario actualizado")
            return ResponseEntity(customResponseEntity, HttpStatus.OK)
        } else {
            val customResponseEntity = CustomResponseEntity<User?>(null, "Usuario no encontrado")
            return ResponseEntity(customResponseEntity, HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long): ResponseEntity<CustomResponseEntity<String>> {
        val user = usersRepository.findById(id)

        if (user.isPresent){
            usersRepository.deleteById(id)
            val customResponseEntity = CustomResponseEntity<String>(null, "Usuario eliminado")
            return ResponseEntity(customResponseEntity, HttpStatus.OK)
        } else {
            val customResponseEntity = CustomResponseEntity<String>(null, "Usuario no encontrado")
            return ResponseEntity(customResponseEntity, HttpStatus.NOT_FOUND)
        }
    }
}