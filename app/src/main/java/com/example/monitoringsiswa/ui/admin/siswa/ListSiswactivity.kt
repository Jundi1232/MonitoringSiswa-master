package com.example.monitoringsiswa.ui.admin.siswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monitoringsiswa.R
import com.example.monitoringsiswa.data.remote.entity.siswa.Siswa
import com.example.monitoringsiswa.data.remote.entity.utils.ViewModelFactorySiswa
import com.example.monitoringsiswa.databinding.ActivityListSiswaBinding

class ListSiswactivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private lateinit var binding: ActivityListSiswaBinding
    private lateinit var siswaAdapter: SiswaAdapter
    private val viewModel: SiswaViewModel by viewModels {
        ViewModelFactorySiswa.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListSiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        siswaAdapter= SiswaAdapter()
        binding.fab.setOnClickListener {
            val intent= Intent(this, AddSiswaActivity::class.java)
            startActivity(intent)
        }
        binding.rvProduct.apply {
            adapter=siswaAdapter
            setHasFixedSize(true)
            layoutManager=
                LinearLayoutManager(this@ListSiswactivity, LinearLayoutManager.VERTICAL,false)
        }
        viewModel.getAllGuru().observe(this){
            siswaAdapter.setData(it)
            Log.d("coba","$it")
        }
        recycler= binding.rvProduct
        initAction()
        siswaAdapter.setDeleteItemClickCallback(object : SiswaAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Siswa) {
                viewModel.delete(data)
            }
        })
    }

    private fun initAction() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val task = (viewHolder as SiswaAdapter.SiswaViewHolder).getWali
                viewModel.delete(task)
            }

        })
        itemTouchHelper.attachToRecyclerView(recycler)
    }
}