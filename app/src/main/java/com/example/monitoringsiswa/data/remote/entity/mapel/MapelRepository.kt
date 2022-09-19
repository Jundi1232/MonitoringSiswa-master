package com.example.monitoringsiswa.data.remote.entity.mapel

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.monitoringsiswa.data.remote.entity.guru.Guru
import com.example.monitoringsiswa.data.remote.entity.guru.GuruDao
import com.example.monitoringsiswa.data.remote.entity.guru.GuruDatabase
import com.example.monitoringsiswa.data.remote.entity.guru.GuruRepository

class MapelRepository(private val dao: MapelDao) {

    suspend fun insertMapel(mapel: Mapel):Long{
        return dao.insertMapel(mapel)
    }

     fun getAllMapel(): LiveData<List<Mapel>> {
        return dao.getAllMapel()
    }
    fun getMapel(): LiveData<List<Mapel>> {
        return dao.getMapel()
    }
    suspend fun deleteMapel(mapel: Mapel){
        dao.deleteMapel(mapel)
    }

    companion object {

        @Volatile
        private var instance: MapelRepository? = null

        fun getInstance(context: Context): MapelRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = MapelDatabase.getInstance(context)
                    instance = MapelRepository(database.mapelDao())
                }
                return instance as MapelRepository
            }

        }
    }
}