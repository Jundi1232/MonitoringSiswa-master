package com.example.monitoringsiswa.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.monitoringsiswa.R
import com.example.monitoringsiswa.data.remote.entity.walmur.WaliMurid
import com.example.monitoringsiswa.databinding.ActivityAdminBinding
import com.example.monitoringsiswa.ui.admin.guru.GuruMainActivity
import com.example.monitoringsiswa.ui.admin.mapel.MapelMainActivity
import com.example.monitoringsiswa.ui.admin.siswa.AddSiswaActivity
import com.example.monitoringsiswa.ui.admin.walmur.ListMainActivity

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            tvGuru.setOnClickListener {
                val intent=Intent(this@AdminActivity, GuruMainActivity::class.java)
                startActivity(intent)
            }
            tvMapel.setOnClickListener {
                val intent=Intent(this@AdminActivity, MapelMainActivity::class.java)
                startActivity(intent)
            }
            tvWali.setOnClickListener {
                val intent=Intent(this@AdminActivity, ListMainActivity::class.java)
                startActivity(intent)
            }
            tvSiswa.setOnClickListener {
                val intent=Intent(this@AdminActivity, AddSiswaActivity::class.java)
                startActivity(intent)
            }
        }
    }
}