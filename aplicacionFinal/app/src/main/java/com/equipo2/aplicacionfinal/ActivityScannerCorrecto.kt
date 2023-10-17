package com.equipo2.aplicacionfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ActivityScannerCorrecto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_correcto) // Asegúrate de que el layout tenga este nombre

        val curp = intent.getStringExtra("curp")
        val apellido1 = intent.getStringExtra("apellido1")
        val apellido2 = intent.getStringExtra("apellido2")
        val nombreCompleto = intent.getStringExtra("nombreCompleto")
        val sexo = intent.getStringExtra("sexo")
        val fechaNacimiento = intent.getStringExtra("fechaNacimiento")
        val pais = intent.getStringExtra("pais")
        val numero = intent.getStringExtra("numero")

        val textViewCurp = findViewById<TextView>(R.id.textViewCurp)
        val textViewApellido1 = findViewById<TextView>(R.id.textViewApellido1)
        val textViewApellido2 = findViewById<TextView>(R.id.textViewApellido2)
        val textViewNombreCompleto = findViewById<TextView>(R.id.textViewNombreCompleto)
        val textViewSexo = findViewById<TextView>(R.id.textViewSexo)
        val textViewFechaNacimiento = findViewById<TextView>(R.id.textViewFechaNacimiento)
        val textViewPais = findViewById<TextView>(R.id.textViewPais)
        val textViewNumero = findViewById<TextView>(R.id.textViewNumero)

        // Asignar valores a los TextViews
        textViewCurp.text = "CURP: $curp"
        textViewApellido1.text = "Apellido Paterno: $apellido1"
        textViewApellido2.text = "Apellido Materno: $apellido2"
        textViewNombreCompleto.text = "Nombre Completo: $nombreCompleto"
        textViewSexo.text = "Sexo: $sexo"
        textViewFechaNacimiento.text = "Fecha de Nacimiento: $fechaNacimiento"
        textViewPais.text = "País: $pais"
        textViewNumero.text = "Número: $numero"

        val restartButton = findViewById<Button>(R.id.btnReiniciar)
        restartButton.setOnClickListener {
            finish()
            startActivity(Intent(this, ActivityScanner::class.java))
        }
    }
}
