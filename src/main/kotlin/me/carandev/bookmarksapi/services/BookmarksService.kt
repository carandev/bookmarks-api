package me.carandev.bookmarksapi.services

import me.carandev.bookmarksapi.dtos.requests.bookmarks.CreateBookmarkRequest
import me.carandev.bookmarksapi.dtos.requests.bookmarks.UpdateBookmarkRequest
import me.carandev.bookmarksapi.dtos.responses.BookmarkResponse
import me.carandev.bookmarksapi.repositories.BookmarksRepository
import me.carandev.bookmarksapi.repositories.UsersRepository
import me.carandev.bookmarksapi.utils.exceptions.ConflictException
import me.carandev.bookmarksapi.utils.exceptions.NotFoundException
import org.springframework.stereotype.Service

@Service
class BookmarksService(val bookmarkRepository: BookmarksRepository, val userRepository: UsersRepository) {
    /**
     * Crea un marcador.
     * @param bookmarkRequest Solicitud para crear un marcador.
     * @return La respuesta del marcador creado.
     * @throws ConflictException Si la url del marcador ya existe.
     */
    fun create(bookmarkRequest: CreateBookmarkRequest): BookmarkResponse {

        if (!userRepository.existsById(bookmarkRequest.userId)) {
            throw NotFoundException("El usuario con el id ${bookmarkRequest.userId} no existe.")
        }

        if (bookmarkRepository.existsBookmarkByUrl(bookmarkRequest.url)) {
            throw ConflictException("El marcador con la URL ${bookmarkRequest.url} ya está registrado.")
        }

        val createdBookmark = bookmarkRepository.save(bookmarkRequest.toBookmark())

        return BookmarkResponse(createdBookmark.id, createdBookmark.url, createdBookmark.title)
    }

    /**
     * Lista los marcadores.
     * @return La lista de marcadores.
     */
    fun list(): List<BookmarkResponse> = bookmarkRepository.findAllBookmarks()

    /**
     * Encuentra un marcador por su identificador.
     * @param id Identificador del marcador.
     * @return La respuesta del marcador encontrado.
     * @throws NotFoundException Si el marcador no se encuentra.
     */
    fun findById(id: Long): BookmarkResponse {
        return bookmarkRepository.findBookmarkById(id) ?: throw NotFoundException("Marcador no encontrado")
    }

    /**
     * Actualiza un marcador por su identificador.
     * @param id Identificador del marcador.
     * @param bookmarkRequest Datos para actualizar el marcador.
     * @return La respuesta del marcador actualizado.
     * @throws NotFoundException Si el marcador no se encuentra.
     */
    fun updateById(id: Long, bookmarkRequest: UpdateBookmarkRequest): BookmarkResponse {
        val bookmarkToUpdate = bookmarkRepository.findById(id)

        if (bookmarkToUpdate.isPresent) {
            val updatedBookmark = bookmarkToUpdate.get().copy(title = bookmarkRequest.title, url = bookmarkRequest.url)
            bookmarkRepository.save(updatedBookmark)

            return BookmarkResponse(updatedBookmark.id, updatedBookmark.url, updatedBookmark.title)
        }

        throw NotFoundException("Marcador no encontrado")
    }

    /**
     * Elimina un marcador por su identificador.
     * @param id Identificador del marcador.
     * @return La respuesta del marcador eliminado.
     * @throws NotFoundException Si el marcador no se encuentra.
     */
    fun deleteById(id: Long): BookmarkResponse {
        val bookmark = bookmarkRepository.findById(id)

        if (bookmark.isPresent) {
            val bookmarkToDelete = bookmark.get()
            val deletedUser = BookmarkResponse(bookmarkToDelete.id, bookmarkToDelete.url, bookmarkToDelete.title)
            bookmarkRepository.deleteById(id)
            return deletedUser
        }

        throw NotFoundException("Usuario no encontrado")
    }
}