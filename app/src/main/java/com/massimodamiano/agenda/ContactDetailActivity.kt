package com.massimodamiano.agenda

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.massimodamiano.agenda.data.ContactRepository

class ContactDetailActivity : AppCompatActivity() {
    private val repository by lazy { ContactRepository(this) }
    private val contactId by lazy { intent.getLongExtra("contact_id", -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_contact_detail)
        findViewById<MaterialButton>(R.id.btnEdit).setOnClickListener {
            startActivity(Intent(this, ContactFormActivity::class.java).putExtra("contact_id", contactId))
        }
        findViewById<MaterialButton>(R.id.btnDelete).setOnClickListener { repository.delete(contactId); finish() }
    }

    override fun onResume() {
        super.onResume(); val contact = repository.getById(contactId) ?: return finish()
        findViewById<TextView>(R.id.tvDetailName).text = getString(R.string.full_name, contact.firstName, contact.lastName)
        findViewById<TextView>(R.id.tvDetailPhone).text = contact.phone
        findViewById<TextView>(R.id.tvDetailAddress).text = contact.address
    }
}
