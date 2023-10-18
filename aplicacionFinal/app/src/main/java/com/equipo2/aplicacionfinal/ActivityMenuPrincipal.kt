package com.equipo2.aplicacionfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class ActivityMenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val cardScanner = findViewById<CardView>(R.id.card_qr)
        val cardScannerAyudantes = findViewById<CardView>(R.id.card_qrayudantes)

        cardScanner.setOnClickListener{
            val intent = Intent(this, ActivityScanner::class.java)
            startActivity(intent)
        }
        cardScannerAyudantes.setOnClickListener(){
            val intent = Intent(this, ActivityScannerAyudantes::class.java)
            startActivity(intent)
        }

    }
}