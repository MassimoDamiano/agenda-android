package com.massimodamiano.agenda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.massimodamiano.agenda.domain.Contact

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        val contacts = listOf(
            Contact(1, "Ada", "Lovelace", "+54 11 5555-0101"),
            Contact(2, "Alan", "Turing", "+54 11 5555-0102")
        )
        findViewById<RecyclerView>(R.id.rvContacts).apply {
            layoutManager = LinearLayoutManager(this@ContactsActivity)
            adapter = ContactsAdapter(contacts)
        }
    }
}
