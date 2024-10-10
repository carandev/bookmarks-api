package me.carandev.bookmarksapi.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import me.carandev.bookmarksapi.utils.rules.BookmarkTagRules

/**
 *  Entidad que representa una etiqueta de un marcador.
 *
 *  @property id Identificador de la etiqueta.
 *  @property name Nombre de la etiqueta.
 */
@Entity
@Table(name = "tags")
data class Tag(
    /**
     *  Identificador de la etiqueta.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    /**
     *  Nombre de la etiqueta.
     */
    @Column(nullable = false, length = BookmarkTagRules.MAX_NAME_LENGTH)
    @NotEmpty
    val name: String,

    /**
     *  Marcadores que tienen esta etiqueta.
     */
    @ManyToMany(mappedBy = "tags")
    val bookmarks: List<Bookmark> = emptyList()
)
