package com.equipo2.mlkitscan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIniciar = findViewById<Button>(R.id.iniciarApp)

        btnIniciar.setOnClickListener{
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
                    val intent = Intent(this, ReinicioActivity::class.java)
                    intent.putExtra("barcodeValue", barcodeValue)
                    startActivity(intent)
                }
                .addOnCanceledListener {
                    // Tarea cancelada
                }
                .addOnFailureListener { e ->
                    // Tarea fallida con una excepción
                }
        }
    }
}



