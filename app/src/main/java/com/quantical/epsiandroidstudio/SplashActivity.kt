package com.quantical.epsiandroidstudio

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            if (readSharedPreferences("firstName") === "Not found"){
                val newIntent= Intent(application, FormActivity::class.java)
                startActivity(newIntent)
                finish()
            }
            else {
                val newIntent= Intent(application, FragmentActivity::class.java)
                startActivity(newIntent)
                finish()
            }
        },2000)
    }

    fun readSharedPreferences(key : String) : String{
        val sharedPreferences= getSharedPreferences("epsi", Context.MODE_PRIVATE)
        val txt=sharedPreferences.getString(key,"Not found")
        return txt.toString()
    }
}
