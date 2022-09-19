package com.example.monitoringsiswa.ui.admin.walmur

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.monitoringsiswa.data.remote.entity.guru.Guru
import com.example.monitoringsiswa.data.remote.entity.walmur.WaliMurid
import com.example.monitoringsiswa.databinding.ItemGuruBinding

class WalMurAdapter:RecyclerView.Adapter<WalMurAdapter.WalMurViewHolder>() {
    private val listWalmur=ArrayList<WaliMurid>()
    private var onDeleteItemClickCallback: OnItemClickCallback? = null
    fun setDeleteItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onDeleteItemClickCallback = onItemClickCallback
    }

    fun setData(list: List<WaliMurid>){
        listWalmur.clear()
        listWalmur.addAll(list)
        notifyDataSetChanged()
    }


    inner class WalMurViewHolder(private val binding: ItemGuruBinding): RecyclerView.ViewHolder(binding.root) {
       lateinit var getWali:WaliMurid

        fun bind(data: WaliMurid){
            getWali=data
            binding.apply {
                tvName.text=data.nama
                tvMapel.text=data.nis
                btnHapus.setOnClickListener {
                    onDeleteItemClickCallback?.onItemClicked(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalMurAdapter.WalMurViewHolder {
        val view= ItemGuruBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WalMurViewHolder(view)
    }

    override fun onBindViewHolder(holder: WalMurAdapter.WalMurViewHolder, position: Int) {
        holder.bind(listWalmur[position])
    }

    override fun getItemCount(): Int =listWalmur.size
    interface OnItemClickCallback {
        fun onItemClicked(data: WaliMurid)
    }
}