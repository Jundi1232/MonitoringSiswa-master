package com.example.monitoringsiswa.data.remote.entity.walmur

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.monitoringsiswa.data.remote.entity.siswa.Siswa

@Dao
interface WalMurDao {
    @Query("Select * from walmur")
    fun getAllWalMur(): LiveData<List<WaliMurid>>
//
//    @Query("Select * from walmur")
//    fun getMapel(): LiveData<List<WaliMurid>>
    @Insert
    suspend fun insertWalMur(waliMurid: WaliMurid): Long

    @Delete
    suspend fun deleteWalMur(waliMurid: WaliMurid)
}