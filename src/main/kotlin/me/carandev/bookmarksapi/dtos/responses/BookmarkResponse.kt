package me.carandev.bookmarksapi.dtos.responses

/**
 * Este es el archivo de la respuesta de un marcador.
 * @property id Identificador del marcador.
 * @property url URL del marcador.
 * @property title Título del marcador.
 */
data class BookmarkResponse(val id: Long, val url: String, val title: String)
