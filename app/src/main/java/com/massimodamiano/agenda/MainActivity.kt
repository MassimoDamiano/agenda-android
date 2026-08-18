package com.massimodamiano.agenda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/** Pantalla inicial temporal del proyecto basado en Views/XML. */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
