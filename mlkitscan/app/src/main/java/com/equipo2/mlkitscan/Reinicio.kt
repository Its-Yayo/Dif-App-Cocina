package com.equipo2.mlkitscan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

class ReinicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el escáner
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE
            )
            .enableAutoZoom()
            .build()

        val scanner = GmsBarcodeScanning.getClient(this)
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                // Tarea completada con éxito
                val barcodeValue = barcode.rawValue.toString()

                // Configura la vista después del escaneo
                setContentView(R.layout.pantalla_de_reinicio)

                val textView = findViewById<TextView>(R.id.textoCodigo)
                textView.text = "Barcode value: $barcodeValue"

                val restartButton = findViewById<Button>(R.id.btnReiniciar)
                restartButton.setOnClickListener {
                    finish()
                    startActivity(Intent(this, ReinicioActivity::class.java))
                }
            }
            .addOnCanceledListener {
                // Tarea cancelada
            }
            .addOnFailureListener { e ->
                // Tarea fallida con una excepción
            }
    }
}
