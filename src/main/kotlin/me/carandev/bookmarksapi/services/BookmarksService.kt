package me.carandev.bookmarksapi.services

import me.carandev.bookmarksapi.models.dtos.requests.bookmarks.CreateBookmarkRequest
import me.carandev.bookmarksapi.models.dtos.requests.bookmarks.UpdateBookmarkRequest
import me.carandev.bookmarksapi.models.dtos.responses.BookmarkResponse
import me.carandev.bookmarksapi.models.entities.Tag
import me.carandev.bookmarksapi.repositories.BookmarksRepository
import me.carandev.bookmarksapi.repositories.TagsRepository
import me.carandev.bookmarksapi.repositories.UsersRepository
import me.carandev.bookmarksapi.utils.exceptions.ConflictException
import me.carandev.bookmarksapi.utils.exceptions.NotFoundException
import org.springframework.stereotype.Service

@Service
class BookmarksService(
    val bookmarkRepository: BookmarksRepository,
    val userRepository: UsersRepository,
    val tagsRepository: TagsRepository
) {
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

        val createdBookmark = bookmarkRepository
            .save(bookmarkRequest.toBookmark(getBookmarkTags(bookmarkRequest.tags)))

        return BookmarkResponse(
            createdBookmark.id,
            createdBookmark.url,
            createdBookmark.title,
            createdBookmark.tags.map { it.name })
    }

    /**
     * Lista los marcadores.
     * @return La lista de marcadores.
     */
    fun list(): List<BookmarkResponse> = bookmarkRepository.findAllBookmarks()
        .map { BookmarkResponse(it.getId(), it.getUrl(), it.getTitle(), it.getTags() ?: emptyList()) }

    /**
     * Encuentra un marcador por su identificador.
     * @param id Identificador del marcador.
     * @return La respuesta del marcador encontrado.
     * @throws NotFoundException Si el marcador no se encuentra.
     */
    fun findById(id: Long): BookmarkResponse {
        val bookmarkProjection = bookmarkRepository.findBookmarkById(id)

        if (bookmarkProjection != null) {
            return BookmarkResponse(
                bookmarkProjection.getId(),
                bookmarkProjection.getUrl(),
                bookmarkProjection.getTitle(),
                bookmarkProjection.getTags() ?: emptyList()
            )
        }

        throw NotFoundException("Marcador no encontrado")
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
            val updatedBookmark =
                bookmarkToUpdate.get().copy(
                    title = bookmarkRequest.title,
                    url = bookmarkRequest.url,
                    tags = getBookmarkTags(bookmarkRequest.tags)
                )
            bookmarkRepository.save(updatedBookmark)

            return BookmarkResponse(
                updatedBookmark.id,
                updatedBookmark.url,
                updatedBookmark.title,
                updatedBookmark.tags.map { it.name })
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
            val deletedUser = BookmarkResponse(
                bookmarkToDelete.id,
                bookmarkToDelete.url,
                bookmarkToDelete.title,
                bookmarkToDelete.tags.map { it.name })
            bookmarkRepository.deleteById(id)
            return deletedUser
        }

        throw NotFoundException("Usuario no encontrado")
    }

    /**
     *  Obtiene los tags de los marcadores de la base de datos.
     *  @param tags Lista de los nombres de los tags.
     *  @return Lista de tags.
     */
    fun getBookmarkTags(tags: List<String>): List<Tag> {
        val tagsList: MutableList<Tag> = mutableListOf()

        tags.forEach {
            val tag = tagsRepository.getTagByNameEqualsIgnoreCase(it)

            if (tag != null) {
                tagsList.add(Tag(id = tag.getId(), name = tag.getName()))
            } else {
                tagsList.add(tagsRepository.save(Tag(name = it)))
            }
        }

        return tagsList
    }
}