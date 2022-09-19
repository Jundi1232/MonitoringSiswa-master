package com.example.monitoringsiswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.monitoringsiswa.Constanta.EXTRA_DATA
import com.example.monitoringsiswa.data.remote.entity.login.Login
import com.example.monitoringsiswa.data.remote.entity.utils.SessionManager
import com.example.monitoringsiswa.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
private lateinit var binding:ActivityMainBinding
private lateinit var sessionManager: SessionManager
  
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager=SessionManager(this)
     val data=intent.getParcelableExtra<Login>(EXTRA_DATA)
        when(data?.level){
            "Walas" -> {

            }
            "Guru" -> {
               binding.tvKoslsutasi.visibility=View.INVISIBLE
                binding.imgKolsutasi.visibility=View.INVISIBLE
            }
            "Siswa" -> {
                binding.apply {
                    tvNamesiswa.text=data.username

                }
            }
        }

    }
}