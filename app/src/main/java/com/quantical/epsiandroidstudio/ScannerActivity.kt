package com.quantical.epsiandroidstudio

import android.app.Activity
import android.app.AlertDialog
import android.app.SearchManager
import android.content.DialogInterface
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.quantical.epsiandroidstudio.R
import android.content.Intent
import android.graphics.Insets.add
import android.net.Uri
import android.widget.ImageButton
import android.widget.TextView
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONObject
import java.lang.Exception


class ScannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        val textView: TextView = findViewById(R.id.textView)
        val qrButton: ImageButton = findViewById(R.id.qr_button)
        qrButton.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
            intentIntegrator.initiateScan()
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result != null) {
            print(result.toString())
            val data = result.toString()
            if(data !=null){
                AlertDialog.Builder(this)
                    .setMessage("Would you like to go to ${result.contents}?")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                        val jsOb= JSONObject(data)
                        val jsArray =jsOb.getJSONArray("items")
                        val jsInfo = jsArray.getJSONObject(0)
                        val firstName = jsInfo.optString("firstName", "")
                        val lastName = jsInfo.optString("lastName", "")
                        val email = jsInfo.optString("email", "")
                        val address = jsInfo.optString("address", "")
                        val zipcode = jsInfo.optString("zipcode", "")
                        val city = jsInfo.optString("city", "")
                        val cardRef = jsInfo.optString("cardRef", "")
                        val info = Info(firstName, lastName, email, address, zipcode, city, cardRef)
                        //Info.add(info)
                        val newIntent= Intent(applicationContext,InscriptionActivity::class.java)
                        newIntent.putExtra("firstName", info.firstName)
                        newIntent.putExtra("lastName", info.lastName)
                        newIntent.putExtra("email", info.email)
                        newIntent.putExtra("address", info.address)
                        newIntent.putExtra("zipcode", info.zipcode)
                        newIntent.putExtra("city", info.city)
                        newIntent.putExtra("cardRef", info.cardRef)
                        applicationContext.startActivity(newIntent)
                        finish()
                    })
                    .setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->  })
                    .create()
                    .show()



            }

        }
    }
}