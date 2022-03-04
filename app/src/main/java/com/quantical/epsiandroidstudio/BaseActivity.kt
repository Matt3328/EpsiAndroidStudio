package com.quantical.epsiandroidstudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

open class BaseActivity : AppCompatActivity() {
    fun showHeaderTitle(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun showBtnBack(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility=View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun showBtnUser(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewAvatar)
        imageViewBack.visibility=View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application, CompteDetailActivity::class.java)
            startActivity(newIntent)
        })
    }

    fun setHeaderTitle(txt : String){
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.text = txt
    }

    fun showHeaderLogo(){
        val imageViewAvatar=findViewById<ImageView>(R.id.imageViewAvatar)
        imageViewAvatar.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application,CompteDetailActivity::class.java)
            startActivity(newIntent)
        })
    }
}