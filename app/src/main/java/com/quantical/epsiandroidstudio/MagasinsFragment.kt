package com.quantical.epsiandroidstudio

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
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
                googleMap.isMyLocationEnabled=true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                googleMap.isMyLocationEnabled=true
            } else -> {
        }
        }
    }


    private val callback = OnMapReadyCallback { googleMap ->

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL ="https://djemam.com/epsi/stores.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if (data != null) {
                    val jsOb = JSONObject(data)
                    val items = jsOb.getJSONArray("stores")
                    for (i in 0 until items.length()) {
                        val magasin = items.getJSONObject(i)
                        val name =magasin.optString("name","")
                        val pictureStore =magasin.optString("pictureStore","")
                        val address =magasin.optString("address","")
                        val zipcode =magasin.optString("zipcode","")
                        val city =magasin.optString("city","Paris")
                        val description =magasin.optString("description","000000")
                        val magasinClass = Magasin(name,
                            pictureStore = pictureStore, address = address,
                             zipcode = zipcode, city = city,description = description)

                        val magasinLatLng = LatLng(
                            magasin.optDouble("longitude", 0.0),
                            magasin.optDouble("latitude", 0.0)
                        )
                        val addressComplete =
                            magasin.optString("address", "") + "-" + magasin.optString(
                                "zipcode",
                                ""
                            ) + " " + magasin.optString("city", "")
                        googleMap.addMarker(
                            MarkerOptions().position(magasinLatLng)
                                .title(magasin.optString("name", ""))
                                .snippet(magasin.optString(addressComplete))
                        )
                    }

                    // override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                    googleMap.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
                        override fun onMarkerClick(p0: Marker): Boolean {
                            // val context = holder.contentLayoutProducts.context
                            val newIntent = Intent(context, MagasinDetailActivity::class.java)
                            val address = Magasin::address + " " + Magasin::city + Magasin::zipcode
                            newIntent.putExtra("magasin_name",Magasin::name)
                            newIntent.putExtra("magasin_picture_url",Magasin::pictureStore)
                            newIntent.putExtra("magasin_adresse",address)
                            newIntent.putExtra("product_description",Magasin::description)
                            context.startActivity(newIntent)

                            return false
                        }
                    })
                }
            }
        })
       this.googleMap=googleMap
       locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
    };

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_magasins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val magasinFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        magasinFragment?.getMapAsync(callback)
    }
}
