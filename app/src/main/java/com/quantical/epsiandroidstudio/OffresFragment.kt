package com.quantical.epsiandroidstudio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class OffresFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val offres = arrayListOf<Offre>()

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL ="https://djemam.com/epsi/offers.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if(data !=null){
                    val jsOb= JSONObject(data)
                    val jsArray =jsOb.getJSONArray("items")
                    for(i in 0 until jsArray.length()){
                        val jsOffre = jsArray.getJSONObject(i)
                        val name =jsOffre.optString("name","")
                        val description =jsOffre.optString("description","")
                        val picture_url =jsOffre.optString("picture_url","")
                        val offre = Offre(name, picture_url = picture_url, description = description)
                        offres.add(offre)
                        Log.d("Offre",offre.name)
                    }
                    runOnUiThread(Runnable {
                        OffreAdapter.notifyDataSetChanged()
                    })

                }
            }
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val offres = arrayListOf<Offre>()
        val offreAdapter = OffreAdapter(OffresFragment,offres)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewOffres)
        recyclerView.layoutManager = LinearLayoutManager(OffresFragment)
        recyclerView.adapter = offreAdapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offres, container, false)

    }
}
