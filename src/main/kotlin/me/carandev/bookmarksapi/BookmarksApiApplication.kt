package me.carandev.bookmarksapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookmarksApiApplication

fun main(args: Array<String>) {
    runApplication<BookmarksApiApplication>(*args)
}
