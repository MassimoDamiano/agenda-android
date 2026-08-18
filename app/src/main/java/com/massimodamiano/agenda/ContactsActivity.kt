package com.massimodamiano.agenda

import android.os.Bundle
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
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
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_contacts)
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
        val search = toolbar.menu.findItem(R.id.action_search).actionView as SearchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean { adapter.filter(newText.orEmpty()); return true }
        })
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_logout) { logout(); true } else false
        }
    }

    override fun onResume() { super.onResume(); if (::adapter.isInitialized) refreshContacts() }
    private fun refreshContacts() = adapter.submitList(repository.getAll())

    private fun logout() {
        getSharedPreferences("agenda_session", MODE_PRIVATE).edit().clear().apply()
        startActivity(Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }
}
