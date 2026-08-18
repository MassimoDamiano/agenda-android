package com.massimodamiano.agenda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.massimodamiano.agenda.domain.Contact
import com.massimodamiano.agenda.data.ContactRepository

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        val repository = ContactRepository(this)
        if (repository.getAll().isEmpty()) {
            repository.insert(Contact(firstName = "Ada", lastName = "Lovelace", phone = "+54 11 5555-0101"))
            repository.insert(Contact(firstName = "Alan", lastName = "Turing", phone = "+54 11 5555-0102"))
        }
        val contacts = repository.getAll()
        findViewById<RecyclerView>(R.id.rvContacts).apply {
            layoutManager = LinearLayoutManager(this@ContactsActivity)
            adapter = ContactsAdapter(contacts)
        }
    }
}
