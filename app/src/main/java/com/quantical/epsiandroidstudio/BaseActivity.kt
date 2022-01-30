package com.quantical.epsiandroidstudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

open class BaseActivity : AppCompatActivity() {
    fun showBtnBack(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility= View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun setHeaderTitle(txt : String){
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.text = txt
    }

    fun showBtnProfil(){
        val imageViewAvatar=findViewById<ImageView>(R.id.imageViewAvatar)
        imageViewAvatar.visibility= View.VISIBLE
        imageViewAvatar.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application,CompteDetailActivity::class.java)
            startActivity(newIntent)
        })
    }
}