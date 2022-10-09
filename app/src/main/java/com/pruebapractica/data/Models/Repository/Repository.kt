package com.example.fortnitestats.Models.Repository

import com.pruebapractica.data.Models.ServerResponse
import com.pruebapractica.data.Network.RetrofitLoginInstance
import retrofit2.Response

class Repository {
    suspend fun getHash(hash: HashMap<String?,String?>): Response<ServerResponse?> {
        return RetrofitLoginInstance.api.getHash(hash)
    }
}