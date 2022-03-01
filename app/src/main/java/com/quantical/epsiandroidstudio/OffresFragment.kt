package com.quantical.epsiandroidstudio

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class OffresFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.fragment_offres, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val offreFragment = childFragmentManager.findFragmentById(R.id.recyclerViewOffres)
        val Offres = arrayListOf<Offre>()

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL = "https://djemam.com/epsi/offers.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if(data !=null){
                    val jsOb= JSONObject(data)
                    val jsArray =jsOb.getJSONArray("items")
                    for(i in 0 until jsArray.length()){
                        val jsRayon = jsArray.getJSONObject(i)
                        val name =jsRayon.optString("name","")
                        val description =jsRayon.optString("description","")
                        val picture_url =jsRayon.optString("picture_url","")
                        val offre = Offre(name, description, picture_url)
                        Offres.add(offre)
                    }
                    val recyclerView = getView()?.findViewById<RecyclerView>(R.id.recyclerViewOffres);
                    if (recyclerView != null) {
                        recyclerView.layoutManager = LinearLayoutManager(getContext())
                    }
                    val RayonAdapter = OffreAdapter(Offres)
                    if (recyclerView != null) {
                        recyclerView.adapter = RayonAdapter
                    }

                }
            }
        })
    }
}

