package me.carandev.bookmarksapi.entities

import jakarta.persistence.*

@Entity
@Table( name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column(nullable = false) val name: String,
    @Column(nullable = false) val email: String,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val bookmarks: List<Bookmark> = emptyList()
)