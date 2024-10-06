package me.carandev.bookmarksapi.repositories

import me.carandev.bookmarksapi.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<User, Long>