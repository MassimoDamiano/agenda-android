package com.massimodamiano.agenda.data.remote

import retrofit2.http.GET

interface ContactsApi { @GET("users") suspend fun getUsers(): List<RemoteUser> }
data class RemoteUser(val name: String, val phone: String, val address: RemoteAddress)
data class RemoteAddress(val street: String, val city: String)
