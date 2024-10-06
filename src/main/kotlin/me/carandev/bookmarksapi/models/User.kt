package me.carandev.bookmarksapi.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table( name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
    @Column(nullable = false) val name: String,
    @Column(nullable = false) val email: String
)