package me.carandev.bookmarksapi.controllers

import me.carandev.bookmarksapi.models.dtos.requests.bookmarks.CreateBookmarkRequest
import me.carandev.bookmarksapi.models.dtos.requests.bookmarks.UpdateBookmarkRequest
import me.carandev.bookmarksapi.models.dtos.responses.BookmarkResponse
import me.carandev.bookmarksapi.services.BookmarksService
import me.carandev.bookmarksapi.utils.CustomResponseEntity
import me.carandev.bookmarksapi.utils.UrlPaths
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(UrlPaths.API_BOOKMARKS)
class BookmarksController(val service: BookmarksService) {

    /**
     * Endpoint para crear un marcador.
     * @param request Solicitud para crear un marcador.
     * @return La respuesta del marcador creado.
     */
    @PostMapping
    fun createBookmark(@RequestBody request: CreateBookmarkRequest): ResponseEntity<CustomResponseEntity<BookmarkResponse?>> {
        val bookmarkResponse = service.create(request)

        return ResponseEntity(
            CustomResponseEntity(
                bookmarkResponse,
                true,
                "Marcador creado correctamente: ${bookmarkResponse.id}"
            ), HttpStatus.CREATED
        )
    }

    /**
     * Endpoint para listar los marcadores.
     * @return La lista de marcadores.
     */
    @GetMapping
    fun getBookmarks(): ResponseEntity<CustomResponseEntity<List<BookmarkResponse>>> {
        val bookmarks = service.list()

        val message = if (bookmarks.isEmpty()) "No se encontraron marcadores" else "Marcadores encontrados"
        return ResponseEntity(CustomResponseEntity(bookmarks, true, message), HttpStatus.OK)
    }

    /**
     * Endpoint para encontrar un marcador por su identificador.
     * @param id Identificador del marcador.
     * @return La respuesta del marcador encontrado.
     */
    @GetMapping("/{id}")
    fun getBookmarkById(@PathVariable id: Long): ResponseEntity<CustomResponseEntity<BookmarkResponse?>> {
        val bookmark = service.findById(id)

        return ResponseEntity(
            CustomResponseEntity<BookmarkResponse?>(bookmark, true, "Marcador encontrado"),
            HttpStatus.OK
        )
    }

    /**
     * Endpoint para actualizar un marcador por su identificador.
     * @param id Identificador del marcador.
     * @param request Datos para actualizar el marcador.
     * @return La respuesta del marcador actualizado.
     */
    @PutMapping("/{id}")
    fun updateBookmarkById(
        @PathVariable id: Long,
        @RequestBody request: UpdateBookmarkRequest
    ): ResponseEntity<CustomResponseEntity<BookmarkResponse?>> {
        val bookmarkToUpdate = service.updateById(id, request)
        return ResponseEntity(CustomResponseEntity(bookmarkToUpdate, true, "Marcador actualizado"), HttpStatus.OK)
    }

    /**
     * Endpoint para eliminar un marcador por su identificador.
     * @param id Identificador del marcador.
     * @return La respuesta del marcador eliminado.
     */
    @DeleteMapping("/{id}")
    fun deleteBookmarkById(@PathVariable id: Long): ResponseEntity<CustomResponseEntity<BookmarkResponse?>> {
        val bookmark = service.deleteById(id)
        return ResponseEntity(CustomResponseEntity(bookmark, true, "Marcador eliminado"), HttpStatus.OK)
    }
}