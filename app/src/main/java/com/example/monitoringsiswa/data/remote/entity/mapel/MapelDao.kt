package com.example.monitoringsiswa.data.remote.entity.mapel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.monitoringsiswa.data.remote.entity.guru.Guru

@Dao
interface MapelDao {
    @Query("Select * from mapel")
    fun getAllMapel(): LiveData<List<Mapel>>

    @Query("Select * from mapel")
    fun getMapel(): LiveData<List<Mapel>>
    @Insert
    suspend fun insertMapel(mapel: Mapel): Long

    @Delete
    suspend fun deleteMapel(mapel: Mapel)
}