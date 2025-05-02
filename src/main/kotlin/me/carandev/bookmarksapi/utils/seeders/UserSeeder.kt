package me.carandev.bookmarksapi.utils.seeders

import me.carandev.bookmarksapi.models.dtos.requests.users.CreateUserRequest
import me.carandev.bookmarksapi.services.UsersService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class UserSeeder(private val usersService: UsersService) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if (!usersService.existsUserByEmail("carlosandres0741@gmail.com")) {
            val adminUser = CreateUserRequest(
                "Carlos Gomez",
                "carlosandres0741@gmail.com"
            )

            usersService.create(adminUser)
        }
    }
}