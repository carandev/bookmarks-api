package me.carandev.bookmarksapi.models.dtos.requests.bookmarks

import me.carandev.bookmarksapi.models.entities.Bookmark
import me.carandev.bookmarksapi.models.entities.Tag

/**
 * Este es el archivo de la solicitud de crear un marcador.
 * @property url URL del marcador.
 * @property title Título del marcador.
 * @property userId Identificador del usuario al que pertenece el marcador.
 */
data class CreateBookmarkRequest(val url: String, val title: String, val userId: Long, val tags: List<String>) {
    /**
     * Convierte la solicitud en un marcador.
     * @return El marcador creado.
     */
    fun toBookmark(tags: List<Tag>) = Bookmark(url = url, title = title, tags = tags)
}
