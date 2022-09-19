package com.example.monitoringsiswa.ui.admin.guru

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monitoringsiswa.data.remote.entity.guru.Guru
import com.example.monitoringsiswa.data.remote.entity.guru.GuruRepository
import com.example.monitoringsiswa.data.remote.entity.login.Login
import com.example.monitoringsiswa.data.remote.entity.login.LoginRepository
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel
import com.example.monitoringsiswa.data.remote.entity.mapel.MapelRepository
import kotlinx.coroutines.launch

class GuruViewModel(private val guruRepository: GuruRepository,
                    private val mapelRepository: MapelRepository,private val loginRepository:LoginRepository):ViewModel() {
    fun insertGuru(guru: Guru) = viewModelScope.launch {
        guruRepository.insertGuru(guru)
    }
    fun getAllmapel() : LiveData<List<Mapel>> = mapelRepository.getMapel()
     fun getAllGuru() :LiveData<List<Guru>> = guruRepository.getAllGuru()
    fun getAlGuru() :LiveData<List<Guru>> = guruRepository.getAlGuru()

    fun insertLogin(login: Login)= viewModelScope.launch {
        loginRepository.insert(login)
    }
}