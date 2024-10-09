package me.carandev.bookmarksapi.repositories

import me.carandev.bookmarksapi.models.dtos.projections.IBookmarkProjection
import me.carandev.bookmarksapi.models.entities.Bookmark
import me.carandev.bookmarksapi.models.dtos.responses.BookmarkResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * Repositorio de marcadores.
 */
@Repository
interface BookmarksRepository : JpaRepository<Bookmark, Long> {

    /**
     * Lista todos los marcadores y hace la proyección al DTO.
     * @return Lista de marcadores.
     */
    @Query("SELECT b.id AS id, b.url AS url, b.title AS title, t.name AS tags " +
            "FROM Bookmark b " +
            "LEFT JOIN b.tags t")
    fun findAllBookmarks() : List<IBookmarkProjection>

    /**
     * Busca un marcador por su identificador y hace la proyección al DTO.
     * @param id Identificador del marcador.
     * @return Marcador encontrado.
     */
    @Query("SELECT b.id AS id, b.url AS url, b.title AS title, t.name AS tags FROM Bookmark b LEFT JOIN b.tags t WHERE b.id = :id")
    fun findBookmarkById(id: Long) : IBookmarkProjection?

    /**
     * Verifica si existe un marcador por su URL.
     * @param url URL del marcador.
     *
     */
    fun existsBookmarkByUrl(url: String) : Boolean
}