package com.example.fortnitestats.Models.stats.VM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fortnitestats.Models.Repository.Repository
import com.pruebapractica.data.Models.ViewModels.HashVM

class HashVMFactory (
    private val repository: Repository):ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HashVM(repository) as T
        }
    }