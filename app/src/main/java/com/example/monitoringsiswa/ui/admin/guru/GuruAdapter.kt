package com.example.monitoringsiswa.ui.admin.guru

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.monitoringsiswa.data.remote.entity.guru.Guru
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel
import com.example.monitoringsiswa.databinding.ItemGuruBinding
import com.example.monitoringsiswa.databinding.ItemMapelBinding

class GuruAdapter:RecyclerView.Adapter<GuruAdapter.GuruViewHolder>() {
    private val listGuru =ArrayList<Guru>()
    fun setData(list: List<Guru>){
        listGuru.clear()
        listGuru.addAll(list)
        notifyDataSetChanged()
    }


    inner class GuruViewHolder(private val binding: ItemGuruBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Guru){
            binding.apply {
                tvName.text=data.nama
                tvMapel.text=data.mapel
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuruAdapter.GuruViewHolder {
        val view= ItemGuruBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GuruViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuruAdapter.GuruViewHolder, position: Int) {
        holder.bind(listGuru[position])
    }

    override fun getItemCount(): Int =listGuru.size
}