package com.quantical.epsiandroidstudio

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MagasinAdapter (private val magasins: ArrayList<Magasin>): RecyclerView.Adapter<MagasinAdapter.ViewHolder>() {
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val popupCarte = view.findViewById<LinearLayout>(R.id.contentLayoutProducts)


    }



    // pour le return sur la page Detail de magasin
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val magasin = magasins.get(position)


        holder.popupCarte.setOnClickListener(View.OnClickListener {
            val context = holder.popupCarte.context
            val newIntent = Intent(context, MagasinDetailActivity::class.java)
            newIntent.putExtra("magasin_name",magasin.name)
            newIntent.putExtra("magasin_picture_url",magasin.pictureStore)
            newIntent.putExtra("magasin_adresse",magasin.address)
            newIntent.putExtra("magasin_zipcode_ville",magasin.zipcode + magasin.city)
            newIntent.putExtra("magasin_description",magasin.description)
            context.startActivity(newIntent)
        })

    }
}