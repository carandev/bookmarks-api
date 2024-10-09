package me.carandev.bookmarksapi.models.dtos.projections

interface IBookmarkProjection {
    fun getId(): Long
    fun getUrl(): String
    fun getTitle(): String
    fun getTags(): List<String>?
}