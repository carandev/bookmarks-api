package me.carandev.bookmarksapi.entities

import jakarta.persistence.*

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
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    val title: String = "",
    val url: String = "",

    @ManyToOne @JoinColumn(name = "user_id") val user: User? = null,
)
