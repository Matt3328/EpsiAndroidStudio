package com.quantical.epsiandroidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class InscriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")
        val zipcode = intent.getStringExtra("zipcode")
        val city = intent.getStringExtra("city")
        val cardRef = intent.getStringExtra("cardRef")
        val editFirstName = findViewById<EditText>(R.id.editTextFirstName)
        val editLastName = findViewById<EditText>(R.id.editTextLastName)
        val editEmail = findViewById<EditText>(R.id.editTextEmailAddress)
        val editAddress = findViewById<EditText>(R.id.editTextAddress)
        val editCity = findViewById<EditText>(R.id.editTextCity)
        val editZipcode = findViewById<EditText>(R.id.editTextZipcode)
        val editCardRef = findViewById<EditText>(R.id.editTextCardRef)
        editFirstName.setText(firstName)
        editLastName.setText(lastName)
        editEmail.setText(email)
        editAddress.setText(address)
        editCity.setText(city)
        editZipcode.setText(zipcode)
        editCardRef.setText(cardRef)

    }
}