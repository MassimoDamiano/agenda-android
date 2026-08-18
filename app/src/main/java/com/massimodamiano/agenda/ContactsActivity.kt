package com.massimodamiano.agenda

import android.os.Bundle
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.massimodamiano.agenda.domain.Contact
import com.massimodamiano.agenda.data.ContactRepository

class ContactsActivity : AppCompatActivity() {
    private lateinit var repository: ContactRepository
    private lateinit var adapter: ContactsAdapter
    private val formLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) refreshContacts()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        repository = ContactRepository(this)
        if (repository.getAll().isEmpty()) {
            repository.insert(Contact(firstName = "Ada", lastName = "Lovelace", phone = "+54 11 5555-0101"))
            repository.insert(Contact(firstName = "Alan", lastName = "Turing", phone = "+54 11 5555-0102"))
        }
        adapter = ContactsAdapter(repository.getAll()) { contact ->
            startActivity(Intent(this, ContactDetailActivity::class.java).putExtra("contact_id", contact.id))
        }
        findViewById<RecyclerView>(R.id.rvContacts).apply {
            layoutManager = LinearLayoutManager(this@ContactsActivity)
            adapter = this@ContactsActivity.adapter
        }
        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabAddContact)
            .setOnClickListener { formLauncher.launch(Intent(this, ContactFormActivity::class.java)) }
    }

    override fun onResume() { super.onResume(); if (::adapter.isInitialized) refreshContacts() }
    private fun refreshContacts() = adapter.submitList(repository.getAll())
}
