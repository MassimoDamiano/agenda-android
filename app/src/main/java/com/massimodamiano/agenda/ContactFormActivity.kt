package com.massimodamiano.agenda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.massimodamiano.agenda.data.ContactRepository
import com.massimodamiano.agenda.domain.Contact

class ContactFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_contact_form)
        val repository = ContactRepository(this)
        val id = intent.getLongExtra("contact_id", -1)
        val current = id.takeIf { it >= 0 }?.let(repository::getById)
        val first = findViewById<TextInputEditText>(R.id.etFirstName)
        val last = findViewById<TextInputEditText>(R.id.etLastName)
        val phone = findViewById<TextInputEditText>(R.id.etPhone)
        val address = findViewById<TextInputEditText>(R.id.etAddress)
        current?.let { first.setText(it.firstName); last.setText(it.lastName); phone.setText(it.phone); address.setText(it.address) }

        findViewById<MaterialButton>(R.id.btnCancel).setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        findViewById<MaterialButton>(R.id.btnSave).setOnClickListener {
            val values = listOf(first, last, phone)
            if (values.any { it.text.isNullOrBlank() }) {
                values.filter { it.text.isNullOrBlank() }.forEach { it.error = getString(R.string.required) }
                return@setOnClickListener
            }
            val contact = Contact(id.coerceAtLeast(0), first.text.toString().trim(), last.text.toString().trim(),
                phone.text.toString().trim(), address.text.toString().trim())
            if (current == null) repository.insert(contact) else repository.update(contact)
            setResult(RESULT_OK); finish()
        }
    }
}
