package com.massimodamiano.agenda.data.remote

import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteContactMapperTest {
    @Test
    fun `maps remote user into domain contact`() {
        val remote = RemoteUser("Ada Lovelace", "123", RemoteAddress("Main St", "London"))

        val contact = RemoteContactMapper.map(remote)

        assertEquals("Ada", contact.firstName)
        assertEquals("Lovelace", contact.lastName)
        assertEquals("123", contact.phone)
        assertEquals("Main St, London", contact.address)
    }

    @Test
    fun `supports a user with only one name`() {
        val contact = RemoteContactMapper.map(RemoteUser("Prince", "456", RemoteAddress("First", "City")))
        assertEquals("Prince", contact.firstName)
        assertEquals("", contact.lastName)
    }
}
