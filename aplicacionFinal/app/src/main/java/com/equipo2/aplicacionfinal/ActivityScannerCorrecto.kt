package com.equipo2.aplicacionfinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityScannerCorrecto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_correcto)

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


        val switchDonacion = findViewById<Switch>(R.id.donacion)
        var donativo = ""

        switchDonacion.setOnCheckedChangeListener { _, isChecked ->
            donativo = if (isChecked) {
                "donada"
            } else {
                "cobrada"
            }
            Log.d("SwitchDonacion", "Estado del Switch: $isChecked")

        }
        val spinner = findViewById<Spinner>(R.id.spinner_condicion)

        // Crear un ArrayAdapter usando un array de strings y un layout predefinido
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.opciones_array, // R.array.opciones_array es un array de strings en tu archivo de recursos
            android.R.layout.simple_spinner_item
        )

        // Especificar el layout que se usará cuando se despliegue el Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Asignar el ArrayAdapter al Spinner
        spinner.adapter = adapter

        val restartButton = findViewById<Button>(R.id.btnReiniciar)
        restartButton.setOnClickListener {
            finish()
            startActivity(Intent(this, ActivityScanner::class.java))
        }


    }
}
