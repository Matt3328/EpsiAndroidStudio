package com.quantical.epsiandroidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MagasinDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magasin_detail)

        intent.getStringExtra("magasin_name")?.let { setHeaderTitle(it) }

        val magasinPictureUrl = intent.getStringExtra("magasin_picture_url")
        val magasin_detail_image = findViewById<ImageView>(R.id.magasin_detail_image)
        Picasso.get().load(magasinPictureUrl).into(magasin_detail_image)

        val magasin_detail_adresse = findViewById<TextView>(R.id.magasin_detail_adresse)
        magasin_detail_adresse.text = intent.getStringExtra("magasin_adresse")

        val magasin_detail_zipcode_ville = findViewById<TextView>(R.id.magasin_detail_zipcode_ville)
        magasin_detail_zipcode_ville.text = intent.getStringExtra("magasin_zipcode_ville")

        val magasin_detail_description = findViewById<TextView>(R.id.magasin_detail_description)
        magasin_detail_description.text = intent.getStringExtra("magasin_description")

    }
}