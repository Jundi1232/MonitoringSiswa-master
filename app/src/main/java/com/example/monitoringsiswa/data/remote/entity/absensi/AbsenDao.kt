package com.example.monitoringsiswa.data.remote.entity.absensi

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface AbsenDao {
    @Query("Select * from absen")
    fun getAllAbsen(): LiveData<List<Absen>>

    @Query("Select * from absen where tgl=:tgl")
    fun getAbsenbyTgl(tgl:String): LiveData<List<Absen>>

    @Query("Select * from absen where tgl like:tgl and nis=:nis")
    fun getAbsenbyTglNis(tgl:String, nis:String): LiveData<List<Absen>>

    @Query("Select * from absen where tgl like:tgl and idkelas=:kls")
    fun getAbsenbyTglkelas(tgl:String, kls:String): LiveData<List<Absen>>

    @Query("Select * from absen where tgl like:tgl and idkelas=:kls and nama_mapel=:mapel")
    fun getAbsenbyTglkelasMapel(tgl:String, kls:String, mapel:String): LiveData<List<Absen>>

    @Insert
    suspend fun insertAbsen(absen: Absen): Long

    @Delete
    suspend fun deleteAbsen(absen: Absen)

    @Query("Update absen set ket=:ket where nis=:nis and tgl=:tgl")
    suspend fun update(nis: String,tgl: String, ket:String)

}