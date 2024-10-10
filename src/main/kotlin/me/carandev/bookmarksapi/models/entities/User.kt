package me.carandev.bookmarksapi.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import me.carandev.bookmarksapi.utils.rules.UserRules

/**
 *  Usuario del sistema.
 */
@Entity
@Table( name = "users")
data class User(
    /**
     *  Identificador del usuario.
     */
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,

    /**
     *  Nombre del usuario.
     */
    @Column(nullable = false, length = UserRules.MAX_NAME_LENGTH)
    @NotEmpty
    val name: String,

    /**
     *  Correo del usuario.
     */
    @Column(nullable = false, length = UserRules.MAX_EMAIL_LENGTH)
    @Email(message = "Correo en formato incorrecto")
    val email: String,

    /**
     *  Correo del usuario.
     */
    @Column(nullable = false, length = UserRules.MAX_EMAIL_LENGTH)
    val auth0Id: String,

    /**
     *  Listado de marcadores que ha creado el usuario.
     */
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val bookmarks: List<Bookmark> = emptyList()
)