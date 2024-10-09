package me.carandev.bookmarksapi.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import me.carandev.bookmarksapi.utils.rules.BookmarkRules

/**
 *  Entidad que representa un marcador de un usuario.
 *
 *  @property id Identificador del marcador.
 *  @property title Título del marcador.
 *  @property url URL del marcador.
 *  @property user Usuario al que pertenece el marcador.
 */
@Entity
@Table(name = "bookmarks")
data class Bookmark(
    /**
     *  Identificador del marcador.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,

    /**
     *  Titulo del marcador.
     */
    @Column(nullable = false, length = BookmarkRules.MAX_TITLE_LENGTH)
    @NotEmpty
    val title: String = "",

    /**
     *  URL del marcador.
     */
    @Column(nullable = false, length = BookmarkRules.MAX_URL_LENGTH)
    @NotEmpty
    val url: String = "",

    /**
     *  Usuario al que pertenece el marcador.
     */
    @ManyToOne @JoinColumn(name = "user_id") val user: User? = null,

    /**
     *  Etiquetas del marcador
     */
    @ManyToMany
    @JoinTable(
        name = "bookmark_tag",
        joinColumns = [JoinColumn(name = "bookmark_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    val tags: List<Tag> = emptyList()
)
