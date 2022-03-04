package com.quantical.epsiandroidstudio

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.security.AccessController.getContext

class FragmentActivity : BaseActivity() {

    val CarteTabFragment=CarteFragment()
    val OffresTabFragment=OffresFragment()
    val MagasinsTabFragment=MagasinsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val textViewTabCarte = findViewById<TextView>(R.id.textViewTab1Carte)
        val textViewTabOffres = findViewById<TextView>(R.id.textViewTabOffres)
        val textViewTabMagasins = findViewById<TextView>(R.id.textViewTabMagasins)

        textViewTabCarte.setOnClickListener( View.OnClickListener {
            showTabCarte()
        })
        textViewTabOffres.setOnClickListener( View.OnClickListener {
            showTabOffres()
        })


        textViewTabMagasins.setOnClickListener( View.OnClickListener {
            showTabMagasins()
        })

        showTabCarte()
    }

    private fun showTabCarte() {
        showBtnUser()
        showBtnBack()
        readSharedPreferences("firstName")
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, CarteTabFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("fF") // name can be null
        transaction.commit()
    }

    private fun showTabOffres() {
        showBtnUser()
        showBtnBack()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, OffresTabFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("fF") // name can be null
        transaction.commit()
    }

    private fun showTabMagasins() {
        showBtnUser()
        showBtnBack()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, MagasinsTabFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("sF") // name can be null
        transaction.commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>1)
            super.onBackPressed()
        else
            finish()
    }
    fun readSharedPreferences(key : String) : String{
        val sharedPreferences= getSharedPreferences("epsi", Context.MODE_PRIVATE)
        val txt=sharedPreferences.getString(key,"Not found")
        return txt.toString()
    }


}