package com.massimodamiano.agenda

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private val prefs by lazy { getSharedPreferences("agenda_session", MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (prefs.getBoolean("authenticated", false)) return openAgenda()
        setContentView(R.layout.activity_login)

        val username = findViewById<TextInputEditText>(R.id.etUsername)
        val password = findViewById<TextInputEditText>(R.id.etPassword)
        findViewById<MaterialButton>(R.id.btnLogin).setOnClickListener {
            if (username.text.toString().trim() == "profe" && password.text.toString() == "profe") {
                prefs.edit().putBoolean("authenticated", true).apply()
                openAgenda()
            } else Toast.makeText(this, R.string.invalid_credentials, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openAgenda() {
        startActivity(Intent(this, ContactsActivity::class.java))
        finish()
    }
}
