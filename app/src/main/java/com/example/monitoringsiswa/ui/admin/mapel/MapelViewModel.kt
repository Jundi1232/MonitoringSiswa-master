package com.example.monitoringsiswa.ui.admin.mapel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monitoringsiswa.data.remote.entity.guru.Guru
import com.example.monitoringsiswa.data.remote.entity.guru.GuruRepository
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel
import com.example.monitoringsiswa.data.remote.entity.mapel.MapelRepository
import kotlinx.coroutines.launch

class MapelViewModel(private val mapelRepository: MapelRepository): ViewModel() {
    fun insertMapel(mapel: Mapel) = viewModelScope.launch {
        mapelRepository.insertMapel(mapel)
    }
    fun getAllmapel() : LiveData<List<Mapel>> = mapelRepository.getAllMapel()


}