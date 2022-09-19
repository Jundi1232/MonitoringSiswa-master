package com.example.monitoringsiswa.ui.admin.walmur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monitoringsiswa.R
import com.example.monitoringsiswa.data.remote.entity.walmur.WaliMurid
import com.example.monitoringsiswa.databinding.ActivityListWalmurBinding
import com.example.monitoringsiswa.ui.admin.mapel.AddMapelActivity
import com.example.monitoringsiswa.ui.admin.mapel.MapelAdapter
import com.example.monitoringsiswa.ui.admin.mapel.MapelViewModel
import com.example.monitoringsiswa.ui.admin.mapel.ViewModelFactory

class ListMainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private lateinit var binding: ActivityListWalmurBinding
    private lateinit var walMurAdapter: WalMurAdapter
    private val viewModel: WalMurViewModel by viewModels {
        ViewModelFactoryWalmur.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListWalmurBinding.inflate(layoutInflater)
        setContentView(binding.root)
        walMurAdapter= WalMurAdapter()
        binding.fab.setOnClickListener {
            val intent= Intent(this, AddWalMurActivity::class.java)
            startActivity(intent)
        }
        binding.rvProduct.apply {
            adapter=walMurAdapter
            setHasFixedSize(true)
            layoutManager=
                LinearLayoutManager(this@ListMainActivity, LinearLayoutManager.VERTICAL,false)
        }
        viewModel.getAllWalmur().observe(this){
            walMurAdapter.setData(it)
            Log.d("coba","$it")
        }
        recycler= findViewById(R.id.rv_product)
        initAction()
        walMurAdapter.setDeleteItemClickCallback(object : WalMurAdapter.OnItemClickCallback{
            override fun onItemClicked(data: WaliMurid) {
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
                val task = (viewHolder as WalMurAdapter.WalMurViewHolder).getWali
                viewModel.delete(task)
            }

        })
        itemTouchHelper.attachToRecyclerView(recycler)
    }
}