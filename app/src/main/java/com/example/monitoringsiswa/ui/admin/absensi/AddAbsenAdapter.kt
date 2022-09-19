package com.example.monitoringsiswa.ui.admin.absensi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.monitoringsiswa.R
import com.example.monitoringsiswa.data.remote.entity.absensi.Absen
import com.example.monitoringsiswa.data.remote.entity.siswa.Siswa
import com.example.monitoringsiswa.databinding.ItemAbsenBinding
import com.example.monitoringsiswa.ui.admin.siswa.SiswaAdapter

class AddAbsenAdapter : RecyclerView.Adapter<AddAbsenAdapter.AbsenViewHolder>() {
    private val listabsensi =ArrayList<Siswa>()
    private var onSaveItemClickCallback: OnItemClickCallback? = null
    fun  setSaveItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onSaveItemClickCallback = onItemClickCallback
    }

    fun setData(list: List<Siswa>){
        listabsensi.clear()
        listabsensi.addAll(list)
        notifyDataSetChanged()
    }


    inner class AbsenViewHolder(private val binding: ItemAbsenBinding): RecyclerView.ViewHolder(binding.root) {
       val radioButton: RadioButton=binding.radioNinjas

        fun bind(data: Siswa){
            binding.apply {
                tvName.text=data.nama
                tvMapel.text=data.nis
                radioNinjas.setOnClickListener {
                    onSaveItemClickCallback?.onItemClicked(radioNinjas,"Alfa",data)
                    radioNinjas.isChecked
                }
                radioPirates.setOnClickListener {
                    onSaveItemClickCallback?.onItemClicked(radioPirates,"Hadir",data)
                    radioPirates.isChecked
                }
            }
//            notifyItemRangeChanged(0, roomList.size());
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAbsenAdapter.AbsenViewHolder {
        val view= ItemAbsenBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AbsenViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddAbsenAdapter.AbsenViewHolder, position: Int) {

        holder.bind(listabsensi[position])

    }

    override fun getItemCount(): Int =listabsensi.size


    interface OnItemClickCallback {
        fun onItemClicked(radioButton: RadioButton,ket: String, siswa: Siswa)
    }
}