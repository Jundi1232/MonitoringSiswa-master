package com.example.monitoringsiswa.ui.admin.absensi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monitoringsiswa.data.remote.entity.Kelas
import com.example.monitoringsiswa.data.remote.entity.absensi.Absen
import com.example.monitoringsiswa.data.remote.entity.absensi.AbsenRepository
import com.example.monitoringsiswa.data.remote.entity.siswa.Siswa
import com.example.monitoringsiswa.data.remote.entity.siswa.SiswaRepository
import kotlinx.coroutines.launch

class AbsenViewModel(private val absenRepository: AbsenRepository,
                     private val siswaRepository: SiswaRepository ):ViewModel() {

    fun insertAbsen(absen: Absen)= viewModelScope.launch {
        absenRepository.insertabsen(absen)
        Log.d("coba","$absen")
    }
    fun getAbsenBytgl(tgl:String): LiveData<List<Absen>> =
        absenRepository.getAbsenbyTgl(tgl)
    fun getSiswabyKelas(kls: String): LiveData<List<Siswa>> =
        siswaRepository.getSiswabyKelas(kls)
    fun updateSiswa(absen: Absen)= viewModelScope.launch {
        absenRepository.updateAbsen(absen)
    }
    fun getAllAbsen(): LiveData<List<Absen>> =
        absenRepository.getAllAbsen()
    fun delete(absen: Absen)= viewModelScope.launch {
        absenRepository.deleteAbsen(absen)
    }
}