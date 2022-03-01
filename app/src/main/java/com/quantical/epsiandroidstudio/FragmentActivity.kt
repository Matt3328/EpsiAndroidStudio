package com.quantical.epsiandroidstudio

import android.os.Bundle
import android.view.View
import android.widget.TextView

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
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, CarteTabFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("fF") // name can be null
        transaction.commit()
    }

    private fun showTabOffres() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, OffresTabFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("fF") // name can be null
        transaction.commit()
    }

    private fun showTabMagasins() {
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

}