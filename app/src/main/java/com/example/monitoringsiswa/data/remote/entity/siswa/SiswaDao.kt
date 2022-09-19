package com.example.monitoringsiswa.data.remote.entity.siswa

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel

@Dao
interface SiswaDao {
    @Query("Select * from siswa")
    fun getAllSiswa(): LiveData<List<Siswa>>

    @Query("Select * from siswa where kelas=:kelas ")
    fun getSiswabykelas(kelas: String): LiveData<List<Siswa>>

    @Query("Select * from siswa where nis=:nis ")
    fun getSiswabyNis(nis: String): LiveData<List<Siswa>>


    @Insert
    suspend fun insertSiswa(siswa: Siswa): Long

    @Delete
    suspend fun deleteSiswa(siswa: Siswa)
}