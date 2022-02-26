package com.quantical.epsiandroidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.google.example.mlkit.ScannerActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val newIntent= Intent(application, ScannerActivity::class.java)
            startActivity(newIntent)
            finish()
        },2000)
    }
}
