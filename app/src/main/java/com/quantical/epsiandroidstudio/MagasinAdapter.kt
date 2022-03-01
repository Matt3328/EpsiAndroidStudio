package com.quantical.epsiandroidstudio

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.squareup.picasso.Picasso

class MagasinAdapter (private val magasins: ArrayList<Magasin>): RecyclerView.Adapter<MagasinAdapter.ViewHolder>() {
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){


    }



    // pour le return sur la page Detail de magasin
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val magasin = magasins.get(position)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}