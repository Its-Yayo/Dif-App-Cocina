package com.equipo2.mlkitscan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                val textView = findViewById<TextView>(R.id.resultadoScaneo)
                textView.text = "Barcode value: $barcodeValue"
            }
            .addOnCanceledListener {
                // Tarea cancelada
            }
            .addOnFailureListener { e ->
                // Tarea fallida con una excepción
            }
        // Botón para reiniciar la aplicación
        val restartButton = findViewById<Button>(R.id.reinicio)

        // Función para reiniciar la aplicación
        fun restartApp() {
            // Termina la actividad actual
            finish()

            // Inicia la actividad nuevamente
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Escuchador de clic para el botón de reinicio
        restartButton.setOnClickListener {
            // Llama a la función para reiniciar la aplicación
            restartApp()
        }

    }
}



