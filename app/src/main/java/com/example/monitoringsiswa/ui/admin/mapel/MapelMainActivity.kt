package com.example.monitoringsiswa.ui.admin.mapel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.monitoringsiswa.databinding.ActivityMapelMainBinding

class MapelMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapelMainBinding
    private lateinit var mapelAdapter: MapelAdapter
    private val viewModel:MapelViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMapelMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mapelAdapter= MapelAdapter()
        binding.fab.setOnClickListener {
            val intent= Intent(this, AddMapelActivity::class.java)
            startActivity(intent)
        }
        binding.rvProduct.apply {
            adapter=mapelAdapter
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MapelMainActivity,LinearLayoutManager.VERTICAL,false)
        }
        viewModel.getAllmapel().observe(this){
            mapelAdapter.setData(it)
        }
    }

}