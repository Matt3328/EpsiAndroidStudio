package com.quantical.epsiandroidstudio
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import okhttp3.Callback

class OffreAdapter(private val offres: ArrayList<Offre>): RecyclerView.Adapter<OffreAdapter.ViewHolder>(){

    private lateinit var mList: ArrayList<Offre>

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewDescription = view.findViewById<TextView>(R.id.textViewDescription)
        val imageViewOffre = view.findViewById<ImageView>(R.id.imageViewOffre)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_offre, viewGroup, false)
        Log.d("test","test")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val offre = offres[position]
        Log.d("WS", offre.toString())
        holder.textViewName.text=offre.name
        holder.textViewDescription.text=offre.description
        Picasso.get().load(offre.picture_url).into(holder.imageViewOffre)

    }

    override fun getItemCount(): Int {
        return offres.size
    }
    fun updateAdapter(mDataList: ArrayList<Offre>) {
        this.mList = mDataList
        notifyDataSetChanged()
    }

}