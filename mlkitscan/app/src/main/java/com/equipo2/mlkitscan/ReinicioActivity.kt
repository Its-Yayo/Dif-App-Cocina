package com.equipo2.mlkitscan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.moduleinstall.InstallStatusListener
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

class ReinicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val moduleInstallClient = ModuleInstall.getClient(this)
        val optionalModuleApi = GmsBarcodeScanning.getClient(this)

        // Inicializa el escáner
        leerQR()
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
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error en el scaneo", Toast.LENGTH_SHORT).show()
            }


        //COMPROBAR E INSTALAR LOS MODULOS EN CASO DE SER NECESARIO
        moduleInstallClient
            .areModulesAvailable(optionalModuleApi)
            .addOnSuccessListener {
                if (it.areModulesAvailable()) {
                    println("<SI está instalado")
                    leerQR()
                } else {
                    println("No está instalado")
                    instalarModulo()
                }
            }
            .addOnFailureListener {
                println("Error al verificar módulo")
            }

    }

    private fun instalarModulo() {
        val moduleInstallClient = ModuleInstall.getClient(this)
        val optionalModuleApi = GmsBarcodeScanning.getClient(this)
        val moduleInstallRequest =
            ModuleInstallRequest.newBuilder()
                .addApi(optionalModuleApi)
                .setListener(listener)
                .build()

        moduleInstallClient
            .installModules(moduleInstallRequest)
            .addOnSuccessListener {
                if (it.areModulesAlreadyInstalled()) {
                    // Modules are already installed when the request is sent.
                    println("I N S T A L A D DO ......")
                }
            }
            .addOnFailureListener {
                println("NO SE PUEDE INSTALAR")
            }

    }

    private fun leerQR() {
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
            )
            .enableAutoZoom()
            .build()
    }

    inner class ModuleInstallProgressListener : InstallStatusListener {
        override fun onInstallStatusUpdated(update: ModuleInstallStatusUpdate) {
            // Progress info is only set when modules are in the progress of downloading.
            update.progressInfo?.let {
                val progress = (it.bytesDownloaded * 100 / it.totalBytesToDownload).toInt()
                // Set the progress for the progress bar.
                //progressBar.setProgress(progress)
                println(progress)
            }

            if (isTerminateState(update.installState)) {
                //moduleInstallClient.unregisterListener(this)
            }
        }

        fun isTerminateState(@ModuleInstallStatusUpdate.InstallState state: Int): Boolean {
            return state == ModuleInstallStatusUpdate.InstallState.STATE_CANCELED || state == ModuleInstallStatusUpdate.InstallState.STATE_COMPLETED || state == ModuleInstallStatusUpdate.InstallState.STATE_FAILED
        }
    }

    val listener = ModuleInstallProgressListener()
}

