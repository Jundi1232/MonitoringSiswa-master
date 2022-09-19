package com.example.monitoringsiswa.data.remote.entity.guru

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel
import com.example.monitoringsiswa.data.remote.entity.mapel.MapelDao

class GuruRepository(private val dao: GuruDao) {

    suspend fun insertGuru(guru: Guru):Long{
        return dao.insertGuru(guru)
    }

     fun getAllGuru(): LiveData<List<Guru>>{
        return dao.getAllGuru()
    }
    fun getAlGuru(): LiveData<List<Guru>>{
        return dao.getllGuru()
    }

    suspend fun deleteGuru(guru: Guru){
        dao.deleteGuru(guru)
    }
    companion object {

        @Volatile
        private var instance: GuruRepository? = null

        fun getInstance(context: Context): GuruRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = GuruDatabase.getInstance(context)
                    instance = GuruRepository(database.guruDao())
                }
                return instance as GuruRepository
            }
        }
    }
}