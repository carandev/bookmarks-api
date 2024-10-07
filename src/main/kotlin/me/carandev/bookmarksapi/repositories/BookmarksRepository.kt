package me.carandev.bookmarksapi.repositories

import me.carandev.bookmarksapi.entities.Bookmark
import me.carandev.bookmarksapi.dtos.responses.BookmarkResponse
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
    @Query("SELECT new me.carandev.bookmarksapi.dtos.responses.BookmarkResponse(b.id, b.url, b.title) FROM Bookmark b")
    fun findAllBookmarks() : List<BookmarkResponse>

    /**
     * Busca un marcador por su identificador y hace la proyección al DTO.
     * @param id Identificador del marcador.
     * @return Marcador encontrado.
     */
    @Query("SELECT new me.carandev.bookmarksapi.dtos.responses.BookmarkResponse(b.id, b.url, b.title) FROM Bookmark b WHERE b.id = :id")
    fun findBookmarkById(id: Long) : BookmarkResponse?

    /**
     * Verifica si existe un marcador por su URL.
     * @param url URL del marcador.
     * @return Si existe o no el marcador.
     */
    fun existsBookmarkByUrl(url: String) : Boolean
}