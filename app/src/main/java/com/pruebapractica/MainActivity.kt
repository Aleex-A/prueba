package com.pruebapractica

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fortnitestats.Models.Repository.Repository
import com.example.fortnitestats.Models.stats.VM.HashVMFactory
import com.pruebapractica.Screens.Dashboard
import com.pruebapractica.data.Models.ViewModels.HashVM
import com.pruebapractica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  viewModel: HashVM

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val hashVMFactory = HashVMFactory(repository)
        viewModel = ViewModelProvider(this,hashVMFactory)[HashVM::class.java]

        binding.login.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            binding.progress.indeterminateDrawable


            val params = HashMap<String?, String?>()
            params["email"] = binding.email.text.toString()
            params["password"] = binding.password.text.toString()

            viewModel.getHash(params)

            viewModel.serverResponse.observe(this) {
                try {


                    if (it.isSuccessful) {
                        binding.progress.visibility = View.INVISIBLE
                        if (it.body()?.token != null) {
                            binding.progress.visibility = View.INVISIBLE

                            val intent = Intent(this, Dashboard::class.java)
                            intent.putExtra("Hash", it.body()?.token.toString())
                            startActivity(intent)
                        }
                    }
                }catch (e:Exception){
                    message(e.toString())
                }
            }
        }
    }
    fun message( message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}