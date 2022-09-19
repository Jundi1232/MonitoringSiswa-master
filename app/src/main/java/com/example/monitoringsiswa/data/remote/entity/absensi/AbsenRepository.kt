package com.example.monitoringsiswa.data.remote.entity.absensi

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.monitoringsiswa.data.remote.entity.guru.Guru

class AbsenRepository(private val dao: AbsenDao) {
    suspend fun insertabsen(absen: Absen):Long{
        return dao.insertAbsen(absen)
    }

    fun getAllAbsen(): LiveData<List<Absen>> {
        return dao.getAllAbsen()
    }
    fun getAbsenbyTglNis(tgl:String, nis:String): LiveData<List<Absen>> {
        return dao.getAbsenbyTglNis(tgl,nis)
    }
    fun getAbsenbyTglkelas(tgl:String, kelas:String): LiveData<List<Absen>> {
        return dao.getAbsenbyTglkelas(tgl,kelas)
    }
    fun getAbsenbyTgl(tgl:String): LiveData<List<Absen>> {
        return dao.getAbsenbyTgl(tgl)
    }
    suspend fun deleteAbsen(absen: Absen){
        dao.deleteAbsen(absen)
    }

    suspend fun updateAbsen(absen: Absen){
        dao.update(absen.nis,absen.tgl,absen.ket)
    }
    companion object {

        @Volatile
        private var instance: AbsenRepository? = null

        fun getInstance(context: Context): AbsenRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = AbsenDatabase.getInstance(context)
                    instance = AbsenRepository(database.absenDao())
                }
                return instance as AbsenRepository
            }

        }
    }
}