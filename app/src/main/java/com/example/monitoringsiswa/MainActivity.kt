package com.example.monitoringsiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.monitoringsiswa.Constanta.EXTRA_DATA
import com.example.monitoringsiswa.Constanta.EXTRA_LEVEL
import com.example.monitoringsiswa.data.remote.entity.login.Login
import com.example.monitoringsiswa.data.remote.entity.utils.SessionManager
import com.example.monitoringsiswa.databinding.ActivityMainBinding
import com.example.monitoringsiswa.ui.admin.absensi.AbsenMainActivity
import java.text.DateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
private lateinit var binding:ActivityMainBinding
private lateinit var sessionManager: SessionManager
  
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        sessionManager=SessionManager(this)
        val currentdate= DateFormat.getDateInstance().format(Date())
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

            }else -> {
                binding.tvNamesiswa.text=currentdate
            }
        }
        binding.apply {
            imgAbsen.setOnClickListener {
                goToAbsen(data?.level)
            }
            imgJadwal.setOnClickListener {
                goToJadwal(data?.level)
            }
            imgTugas.setOnClickListener {
                goToTugas(data?.level)
            }
            imgNilai.setOnClickListener {
                goToNila(data?.level)
            }
        }

    }

    private fun goToNila(level: String?) {
        TODO("Not yet implemented")
    }

    private fun goToTugas(level: String?) {

    }

    private fun goToJadwal(level: String?) {
        TODO("Not yet implemented")
    }

    private fun goToAbsen(level: String?) {
        val intent= Intent(this, AbsenMainActivity::class.java)
        intent.putExtra(EXTRA_LEVEL, level)
        startActivity(intent)
    }
}