package com.quantical.epsiandroidstudio

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class CompteDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compte_detail)
        showBtnBack()
        val editFirstName = findViewById<EditText>(R.id.editTextFirstName)
        val editLastName = findViewById<EditText>(R.id.editTextLastName)
        val editEmail = findViewById<EditText>(R.id.editTextEmailAddress)
        val editAddress = findViewById<EditText>(R.id.editTextAddress)
        val editCity = findViewById<EditText>(R.id.editTextCity)
        val editZipcode = findViewById<EditText>(R.id.editTextZipcode)
        val editCardRef = findViewById<EditText>(R.id.editTextCardRef)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        editFirstName.setText(readSharedPreferences("firstName"))
        editLastName.setText(readSharedPreferences("lastName"))
        editEmail.setText(readSharedPreferences("email"))
        editAddress.setText(readSharedPreferences("address"))
        editCity.setText(readSharedPreferences("city"))
        editZipcode.setText(readSharedPreferences("zipcode"))
        editCardRef.setText(readSharedPreferences("cardRef"))

        buttonSave.setOnClickListener(View.OnClickListener {
            writeSharedPreferences("firstName",editFirstName.text.toString())
            writeSharedPreferences("lastName",editLastName.text.toString())
            writeSharedPreferences("email",editEmail.text.toString())
            writeSharedPreferences("address",editAddress.text.toString())
            writeSharedPreferences("city",editCity.text.toString())
            writeSharedPreferences("zipcode",editZipcode.text.toString())
            writeSharedPreferences("cardRef",editCardRef.text.toString())
        })
    }
    fun readSharedPreferences(key : String) : String{
        val sharedPreferences= getSharedPreferences("epsi", Context.MODE_PRIVATE)
        val txt=sharedPreferences.getString(key,"Not found")
        return txt.toString()
    }
    fun writeSharedPreferences(key : String , value : String){
        val sharedPreferences= getSharedPreferences("epsi", Context.MODE_PRIVATE)
        val edit=sharedPreferences.edit()
        edit.putString(key,value)
        edit.apply()
    }
}