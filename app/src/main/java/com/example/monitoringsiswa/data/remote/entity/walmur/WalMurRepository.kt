package com.example.monitoringsiswa.data.remote.entity.walmur

import android.content.Context
import androidx.lifecycle.LiveData

class WalMurRepository(private val dao: WalMurDao) {
    suspend fun insertGuru(wali: WaliMurid):Long{
        return dao.insertWalMur(wali)
    }

    fun getAllGuru(): LiveData<List<WaliMurid>> {
        return dao.getAllWalMur()
    }


    suspend fun deleteGuru(wali: WaliMurid){
        dao.deleteWalMur(wali)
    }
    companion object {

        @Volatile
        private var instance: WalMurRepository? = null

        fun getInstance(context: Context): WalMurRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = WalMurDatabase.getInstance(context)
                    instance = WalMurRepository(database.walmurDao())
                }
                return instance as WalMurRepository
            }

        }
    }
}