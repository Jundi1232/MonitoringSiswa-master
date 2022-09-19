package com.example.monitoringsiswa.ui.admin.absensi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.monitoringsiswa.Constanta.EXTRA_DATA
import com.example.monitoringsiswa.data.remote.entity.absensi.Absen
import com.example.monitoringsiswa.data.remote.entity.guru.Guru
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel
import com.example.monitoringsiswa.data.remote.entity.siswa.Siswa
import com.example.monitoringsiswa.databinding.ItemGuruBinding
import com.example.monitoringsiswa.databinding.ItemMapelBinding
import com.example.monitoringsiswa.ui.admin.siswa.SiswaAdapter

class AbsensiAdapter:RecyclerView.Adapter<AbsensiAdapter.AbsenViewHolder>() {
    private val listabsensi =ArrayList<Absen>()
    private var onDeleteItemClickCallback: OnItemClickCallback? = null
    fun setDeleteItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onDeleteItemClickCallback = onItemClickCallback
    }


    fun setData(list: List<Absen>){
        listabsensi.clear()
        listabsensi.addAll(list)
        notifyDataSetChanged()
    }


    inner class AbsenViewHolder(private val binding: ItemGuruBinding): RecyclerView.ViewHolder(binding.root) {
       lateinit var getabsen: Absen
        fun bind(data: Absen){
            getabsen=data
            binding.apply {
                tvName.text=data.nis
                tvMapel.text=data.ket
                btnHapus.setOnClickListener {
                    onDeleteItemClickCallback?.onItemClicked(data)
                }
            }
            itemView.setOnClickListener {
                val intent= Intent(itemView.context,DetailAbsensiActivity::class.java)
                intent.putExtra(EXTRA_DATA,data)
                itemView.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsensiAdapter.AbsenViewHolder {
        val view= ItemGuruBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AbsenViewHolder(view)
    }

    override fun onBindViewHolder(holder: AbsensiAdapter.AbsenViewHolder, position: Int) {
        holder.bind(listabsensi[position])
    }

    override fun getItemCount(): Int =listabsensi.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Absen)
    }
}