package com.equipo2.mlkitscan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ReinicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_de_reinicio)

        val barcodeValue = intent.getStringExtra("barcodeValue")
        val textView = findViewById<TextView>(R.id.textoCodigo)
        textView.text = "Barcode value: $barcodeValue"

        val restartButton = findViewById<Button>(R.id.btnReiniciar)
        restartButton.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
