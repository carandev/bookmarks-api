package me.carandev.bookmarksapi.utils.rules

/**
 * Objeto que contiene las reglas de los usuarios.
 */
object UserRules {
    /**
     *  Tamaño maximo del nombre del usuario.
     */
    const val MAX_NAME_LENGTH = 100

    /**
     *  Tamaño maximo del correo del usuario.
     */
    const val MAX_EMAIL_LENGTH = 250

    /**
     *  Query para obtener todos los usuarios.
     */
    const val FIND_ALL_USERS_QUERY = "SELECT u.id AS id, u.name AS name, u.email AS email FROM User u";

    const val FIND_USER_BY_ID = "SELECT u.id AS id, u.name AS name, u.email AS email FROM User u WHERE u.id = :id";
}