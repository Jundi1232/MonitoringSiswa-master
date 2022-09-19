package com.example.monitoringsiswa.ui.admin.mapel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel
import com.example.monitoringsiswa.databinding.ItemMapelBinding

class MapelAdapter : RecyclerView.Adapter<MapelAdapter.MapelViewHorder>() {
  private val listMapel =ArrayList<Mapel>()
    fun setData(list: List<Mapel>){
        listMapel.clear()
        listMapel.addAll(list)
        notifyDataSetChanged()
    }

    inner class MapelViewHorder(private val binding: ItemMapelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Mapel){
            binding.apply {
                tvName.text=data.namemapel
                tvId.text=data.kkm.toString()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MapelAdapter.MapelViewHorder {
        val view= ItemMapelBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return MapelViewHorder(view)
    }

    override fun onBindViewHolder(holder: MapelAdapter.MapelViewHorder, position: Int) {
        holder.bind(listMapel[position])
    }

    override fun getItemCount(): Int =listMapel.size
}