package com.pruebapractica.data.Network

import com.pruebapractica.data.Models.ServerResponse
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST(".")
    suspend fun getHash( @FieldMap hash: HashMap<String?,String?> ): Response<ServerResponse?>

}