package me.carandev.bookmarksapi.dtos.requests.bookmarks

import me.carandev.bookmarksapi.entities.Bookmark

/**
    * Este es el archivo de la solicitud de crear un marcador.
    * @property url URL del marcador.
    * @property title Título del marcador.
    * @property userId Identificador del usuario al que pertenece el marcador.
 */
data class CreateBookmarkRequest(val url: String, val title: String, val userId : Long) {
    /**
     * Convierte la solicitud en un marcador.
     * @return El marcador creado.
     */
    fun toBookmark() = Bookmark(url = url, title = title)
}
