package com.example.monitoringsiswa.ui.admin.walmur

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monitoringsiswa.data.remote.entity.guru.Guru
import com.example.monitoringsiswa.data.remote.entity.guru.GuruRepository
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel
import com.example.monitoringsiswa.data.remote.entity.mapel.MapelRepository
import com.example.monitoringsiswa.data.remote.entity.walmur.WalMurRepository
import com.example.monitoringsiswa.data.remote.entity.walmur.WaliMurid
import kotlinx.coroutines.launch

class WalMurViewModel(private val walMurRepository: WalMurRepository):ViewModel() {
    fun insertWalmur(walmur: WaliMurid) = viewModelScope.launch {
        walMurRepository.insertGuru(walmur)
    }
     fun getAllWalmur() :LiveData<List<WaliMurid>> = walMurRepository.getAllGuru()

    fun delete(walmur: WaliMurid) {
        viewModelScope.launch {
            walMurRepository.deleteGuru(walmur)
        }
    }
}