package com.example.monitoringsiswa.data.remote.entity.siswa

import android.content.Context
import androidx.lifecycle.LiveData

class SiswaRepository(private val dao: SiswaDao) {
    suspend fun insertGuru(siswa: Siswa): Long {
        return dao.insertSiswa(siswa)
    }

    fun getAllGuru(): LiveData<List<Siswa>> {
        return dao.getAllSiswa()
    }

    fun getSiswabyKelas(kelas: String): LiveData<List<Siswa>> {
        return dao.getSiswabykelas(kelas)
    }

    fun getSiswabyNis(nis: String): LiveData<List<Siswa>> {
        return dao.getSiswabyNis(nis)
    }

    suspend fun deleteGuru(siswa: Siswa) {
        dao.deleteSiswa(siswa)
    }

    companion object {

        @Volatile
        private var instance: SiswaRepository? = null

        fun getInstance(context: Context): SiswaRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = SiswaDatabase.getInstance(context)
                    instance = SiswaRepository(database.siswaDao())
                }
                return instance as SiswaRepository
            }

        }
    }
}