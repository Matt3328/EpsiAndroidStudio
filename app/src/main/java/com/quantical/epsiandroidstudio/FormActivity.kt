package com.quantical.epsiandroidstudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        val buttonQRCode = findViewById<Button>(R.id.buttonQRCode)
        val buttonInscription = findViewById<Button>(R.id.buttonInscription)
        buttonQRCode.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application, ScannerActivity::class.java)
            startActivity(newIntent)
            finish()
        })
        buttonInscription.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application, InscriptionActivity::class.java)
            startActivity(newIntent)
            finish()
        })
    }
}