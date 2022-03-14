package com.esteban.rodriguezo.bookproject11.server

data class User(
    var uid: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var city: String? = null,
    var role: Role? = null
)

enum class Role {
    VENDEDOR, COMPRADOR, AMBOS
}
