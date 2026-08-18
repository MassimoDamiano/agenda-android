package com.massimodamiano.agenda.data

import android.content.ContentValues
import android.content.Context
import com.massimodamiano.agenda.domain.Contact

class ContactRepository(context: Context) {
    private val helper = ContactDbHelper(context.applicationContext)

    fun insert(contact: Contact): Long = helper.writableDatabase.use { db ->
        db.insert("contacts", null, ContentValues().apply {
            put("first_name", contact.firstName); put("last_name", contact.lastName)
            put("phone", contact.phone); put("address", contact.address); put("gender", contact.gender)
        })
    }

    fun getAll(): List<Contact> = helper.readableDatabase.use { db ->
        db.query("contacts", null, null, null, null, null, "first_name ASC").use { cursor ->
            buildList {
                while (cursor.moveToNext()) add(Contact(
                    cursor.getLong(cursor.getColumnIndexOrThrow("_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("first_name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("last_name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                    cursor.getString(cursor.getColumnIndexOrThrow("address")),
                    cursor.getString(cursor.getColumnIndexOrThrow("gender"))
                ))
            }
        }
    }
}
