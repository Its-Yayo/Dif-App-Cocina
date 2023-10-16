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

        cardScanner.setOnClickListener{
            val intent = Intent(this, ActivityScanner::class.java)
            startActivity(intent)
        }

    }
}