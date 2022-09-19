package com.example.monitoringsiswa.data.remote.entity.guru

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel


@Dao
interface GuruDao {

    @Query("Select * from guru where nip= :nip")
    fun getGuruById(nip: Int): LiveData<Guru>

    @Query("Select * from guru ")
    fun getAllGuru(): LiveData<List<Guru>>
//
    @Query("Select * from guru INNER JOIN mapel ON mapel.name_mapel=guru.mapel ")
    fun getllGuru(): LiveData<List<Guru>>

    @Insert
    suspend fun insertGuru(guru: Guru): Long

    @Delete
    suspend fun deleteGuru(guru: Guru)

}