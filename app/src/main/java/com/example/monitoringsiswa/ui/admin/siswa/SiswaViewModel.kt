package com.example.monitoringsiswa.ui.admin.siswa

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monitoringsiswa.data.remote.entity.guru.Guru
import com.example.monitoringsiswa.data.remote.entity.guru.GuruRepository
import com.example.monitoringsiswa.data.remote.entity.login.Login
import com.example.monitoringsiswa.data.remote.entity.login.LoginRepository
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel
import com.example.monitoringsiswa.data.remote.entity.mapel.MapelRepository
import com.example.monitoringsiswa.data.remote.entity.siswa.Siswa
import com.example.monitoringsiswa.data.remote.entity.siswa.SiswaRepository
import com.example.monitoringsiswa.data.remote.entity.walmur.WaliMurid
import kotlinx.coroutines.launch

class SiswaViewModel(private val siswaRepository: SiswaRepository,private val loginRepository: LoginRepository):ViewModel() {
    fun insertSiswa(siswa: Siswa) = viewModelScope.launch {
        siswaRepository.insertGuru(siswa)
    }
     fun getAllGuru() :LiveData<List<Siswa>> = siswaRepository.getAllGuru()
    fun delete(siswa: Siswa) {
        viewModelScope.launch {
            siswaRepository.deleteGuru(siswa)
        }
    }
    fun insertLogin(login: Login)= viewModelScope.launch {
        loginRepository.insert(login)
    }
}