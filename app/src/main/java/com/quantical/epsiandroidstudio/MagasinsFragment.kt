package com.quantical.epsiandroidstudio

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class MagasinsFragment : Fragment() {



    lateinit var googleMap :GoogleMap
    @SuppressLint("MissingPermission", "UseRequireInsteadOfGet")

    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                googleMap.isMyLocationEnabled=true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                googleMap.isMyLocationEnabled=true
                // Only approximate location access granted.
            } else -> {
            // No location access granted.
        }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = OnMapReadyCallback() { googleMap ->
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL ="https://djemam.com/epsi/stores.json"
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
                    val jsCities= JSONObject(data)
                    val items =jsCities.getJSONArray("stores")
                    for(i in 0 until items.length()){
                        val jsMagasin = items.getJSONObject(i)
                        val name =jsMagasin.optString("name","")
                        val description =jsMagasin.optString("description","")
                        val pictureStore =jsMagasin.optString("pictureStore","Paris")
                        val address =jsMagasin.optString("address","")
                        val zipcode =jsMagasin.optString("zipcode","")
                        val city =jsMagasin.optString("city","")
//                        val longitude =jsMagasin.optString("longitude","")
//                        val latitude =jsMagasin.optString("latitude","")
//                        val magasin = Magasin(storeId = storeId, name = name, description = description, pictureStore = pictureStore,
//                            address = address, city = city,zipcode = zipcode, longitude = longitude,latitude = latitude)
//                        magasins.add(magasin)



                        val magasinLatLng = LatLng(jsMagasin.optDouble("longitude",0.0),jsMagasin.optDouble("latitude",0.0))
                        val addressComplete = jsMagasin.optString("address","") + "-" + jsMagasin.optString("zipcode","") + " " + jsMagasin.optString("city","")
                        googleMap.addMarker(MarkerOptions().position(magasinLatLng).title(jsMagasin.optString("name","")).snippet(jsMagasin.optString(addressComplete)))
                        // how to add a button in googleMap?
                    }

                }
            }
        })
        }
        this.googleMap=googleMap
        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_magasins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}