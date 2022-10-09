package com.pruebapractica.data.Models.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fortnitestats.Models.Repository.Repository

import com.pruebapractica.data.Models.ServerResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class HashVM(private val repository: Repository): ViewModel() {
    private val _serverResponse = MutableLiveData<Response<ServerResponse?>>()
    val serverResponse: LiveData<Response<ServerResponse?>> get() = _serverResponse


    fun getHash(hash: HashMap<String?,String?>) {
        viewModelScope.launch {
            val response = repository.getHash(hash)
            _serverResponse.value = response

        }
    }


}