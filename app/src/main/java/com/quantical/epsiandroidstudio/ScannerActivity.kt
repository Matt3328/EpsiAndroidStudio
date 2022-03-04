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
            val data = result.contents
            if(data !=null){
                AlertDialog.Builder(this)
                    .setMessage("Would you like to go to ${result.contents}?")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                        val jsOb= JSONObject(data)
                        val firstName = jsOb.optString("firstName", "")
                        val lastName = jsOb.optString("lastName", "")
                        val email = jsOb.optString("email", "")
                        val address = jsOb.optString("address", "")
                        val zipcode = jsOb.optString("zipcode", "")
                        val city = jsOb.optString("city", "")
                        val cardRef = jsOb.optString("cardRef", "")
//                        val info = Info(firstName, lastName, email, address, zipcode, city, cardRef)
                        //Info.add(info)
                        val newIntent= Intent(applicationContext,InscriptionActivity::class.java)
                        newIntent.putExtra("firstName", firstName)
                        newIntent.putExtra("lastName", lastName)
                        newIntent.putExtra("email", email)
                        newIntent.putExtra("address", address)
                        newIntent.putExtra("zipcode", zipcode)
                        newIntent.putExtra("city", city)
                        newIntent.putExtra("cardRef", cardRef)
                        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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