package com.example.monitoringsiswa.ui.admin.siswa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.monitoringsiswa.data.remote.entity.siswa.Siswa
import com.example.monitoringsiswa.data.remote.entity.walmur.WaliMurid
import com.example.monitoringsiswa.databinding.ItemGuruBinding

class SiswaAdapter:RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder>() {
    private val listSiswa=ArrayList<Siswa>()
    private var onDeleteItemClickCallback: OnItemClickCallback? = null
    fun setDeleteItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onDeleteItemClickCallback = onItemClickCallback
    }

    fun setData(list: List<Siswa>){
        listSiswa.clear()
        listSiswa.addAll(list)
        notifyDataSetChanged()
    }


    inner class SiswaViewHolder(private val binding: ItemGuruBinding): RecyclerView.ViewHolder(binding.root) {
       lateinit var getWali:Siswa

        fun bind(data: Siswa){
            getWali=data
            binding.apply {
                tvName.text=data.nama
                tvMapel.text=data.jk
                btnHapus.setOnClickListener {
                    onDeleteItemClickCallback?.onItemClicked(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaAdapter.SiswaViewHolder {
        val view= ItemGuruBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SiswaViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiswaAdapter.SiswaViewHolder, position: Int) {
        holder.bind(listSiswa[position])
    }

    override fun getItemCount(): Int =listSiswa.size
    interface OnItemClickCallback {
        fun onItemClicked(data: Siswa)
    }
}