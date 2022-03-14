package com.esteban.rodriguezo.bookproject11.utils

import android.util.Patterns

fun isEmailValid(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}