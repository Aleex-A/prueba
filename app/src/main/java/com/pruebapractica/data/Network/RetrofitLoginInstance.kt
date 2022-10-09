package com.pruebapractica.data.Network

import com.example.fortnitestats.Network.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitLoginInstance {


        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
        val api: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }

}