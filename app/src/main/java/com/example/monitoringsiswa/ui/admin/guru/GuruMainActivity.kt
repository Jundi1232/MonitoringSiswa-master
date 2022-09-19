package com.example.monitoringsiswa.ui.admin.guru

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.monitoringsiswa.databinding.ActivityGuruMain2Binding

class GuruMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var guruAdapter: GuruAdapter
    private lateinit var binding: ActivityGuruMain2Binding
    private val viewModel: GuruViewModel by viewModels {
        ViewModelFactoryguru.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    guruAdapter= GuruAdapter()
        binding = ActivityGuruMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view ->
            val intent= Intent(this, AddGuruActivity::class.java)
            startActivity(intent)
        }
        binding.rvProduct.apply {
            adapter=guruAdapter
            setHasFixedSize(true)
            layoutManager=
                LinearLayoutManager(this@GuruMainActivity, LinearLayoutManager.VERTICAL,false)
        }
        viewModel.getAllGuru().observe(this){
            guruAdapter.setData(it)

        }
    }
}