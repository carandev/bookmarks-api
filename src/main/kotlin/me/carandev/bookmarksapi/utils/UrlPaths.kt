package me.carandev.bookmarksapi.utils

/**
 * Objeto que contiene las rutas de la API.
 */
object UrlPaths {
    private const val API_VERSION = "/v1"
    private const val API_BASE_URL = "/api$API_VERSION"

    const val API_BOOKMARKS = "$API_BASE_URL/bookmarks"
    const val API_USERS = "$API_BASE_URL/users"
}