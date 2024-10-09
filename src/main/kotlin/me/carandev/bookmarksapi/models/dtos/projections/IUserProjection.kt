package me.carandev.bookmarksapi.models.dtos.projections

interface IUserProjection {
    fun getId(): Long
    fun getName(): String
    fun getEmail(): String
}