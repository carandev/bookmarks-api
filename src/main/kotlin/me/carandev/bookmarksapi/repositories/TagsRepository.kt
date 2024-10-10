package me.carandev.bookmarksapi.repositories

import me.carandev.bookmarksapi.models.dtos.projections.ITagProjection
import me.carandev.bookmarksapi.models.entities.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagsRepository : JpaRepository<Tag, Long> {
    fun getTagByNameEqualsIgnoreCase(name: String) : ITagProjection?
}