package com.pruebapractica.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.auth0.android.jwt.JWT
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.pruebapractica.data.Models.ViewModels.HashVM
import com.pruebapractica.databinding.ActivityDashboardBinding


class Dashboard : AppCompatActivity() {
    private lateinit var  viewModel: HashVM

    private lateinit var binding: ActivityDashboardBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val dato = bundle?.getString("Hash")
        val barcode : List<String>? = dato?.split(".")
        Log.d("realy dato", barcode.hashCode().toString())

        val jwt: JWT = JWT(dato.toString())
        val claim: String? = jwt.getClaim("titular" ).asString()
        val titular: String? = jwt.getClaim("titular" ).asString()
        val email: String? = jwt.getClaim("email" ).asString()
        val multiFormatWriter = MultiFormatWriter()

        binding.saludo.text ="Good afternoon $titular"
        binding.settings.setOnClickListener {message("settings")  }
        binding.profile.setOnClickListener { message("Profile $titular") }
        binding.email.setOnClickListener { message("email: $email") }


        Log.d("realy workssss",claim.toString())
        try{
            val bitMatrix: BitMatrix = multiFormatWriter.encode(barcode?.get(0).toString(),BarcodeFormat.CODE_128,650,350, null)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            binding.barImage.setImageBitmap(bitmap)
            binding.barValue.text = dato.toString()
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    fun message( message:String){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}