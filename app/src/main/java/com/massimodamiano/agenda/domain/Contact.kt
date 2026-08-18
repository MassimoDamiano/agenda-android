package com.massimodamiano.agenda.domain

data class Contact(
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val address: String = "",
    val gender: String = "No informado"
)
