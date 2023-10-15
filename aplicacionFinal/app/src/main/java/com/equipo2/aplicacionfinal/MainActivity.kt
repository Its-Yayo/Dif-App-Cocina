package com.equipo2.aplicacionfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Accede a los TextInputEditText usando sus IDs
        val emailEditText: TextInputEditText = findViewById(R.id.usuario)
        val contrasenaEditText: TextInputEditText = findViewById(R.id.Contrasena)

        // Ahora puedes usar emailEditText y contrasenaEditText en tu código
        // por ejemplo, para obtener el texto:

        val emailText = emailEditText.text.toString()
        val contrasenaText = contrasenaEditText.text.toString()
    }
}