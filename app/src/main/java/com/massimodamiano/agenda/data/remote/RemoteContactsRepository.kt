package com.massimodamiano.agenda.data.remote

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

sealed interface SyncState {
    data object Idle : SyncState; data object Loading : SyncState
    data class Success(val count: Int) : SyncState; data class Error(val message: String) : SyncState
}

class RemoteContactsRepository {
    private val api = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create()).build().create(ContactsApi::class.java)
    private val mutableState = MutableStateFlow<SyncState>(SyncState.Idle)
    val state = mutableState.asStateFlow()

    suspend fun fetch() = runCatching {
        mutableState.value = SyncState.Loading
        api.getUsers().map(RemoteContactMapper::map).also { mutableState.value = SyncState.Success(it.size) }
    }.onFailure { mutableState.value = SyncState.Error(it.message ?: "Error de conexión") }
}
