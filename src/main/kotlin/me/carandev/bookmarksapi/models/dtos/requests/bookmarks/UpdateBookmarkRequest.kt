package me.carandev.bookmarksapi.models.dtos.requests.bookmarks

/**
 * Solicitud de actualización de un marcador.
 * @property url URL del marcador.
 * @property title Título del marcador.
 */
data class UpdateBookmarkRequest(val title: String, val url: String, val tags: List<String>)