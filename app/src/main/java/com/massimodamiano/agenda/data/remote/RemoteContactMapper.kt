package com.massimodamiano.agenda.data.remote

import com.massimodamiano.agenda.domain.Contact

object RemoteContactMapper {
    fun map(user: RemoteUser): Contact {
        val names = user.name.trim().split(Regex("\\s+"), limit = 2)
        return Contact(firstName = names.first(), lastName = names.getOrElse(1) { "" },
            phone = user.phone, address = "${user.address.street}, ${user.address.city}")
    }
}
