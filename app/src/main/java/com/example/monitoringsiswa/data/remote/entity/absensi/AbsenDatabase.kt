package com.example.monitoringsiswa.data.remote.entity.absensi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.monitoringsiswa.data.remote.entity.guru.Guru
import com.example.monitoringsiswa.data.remote.entity.guru.GuruDao
import com.example.monitoringsiswa.data.remote.entity.guru.GuruDatabase
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel

@Database(entities = [Absen::class], version = 1)
abstract class AbsenDatabase:RoomDatabase() {

    abstract fun absenDao(): AbsenDao
//    abstract fun mapelDao():MapelDao

    companion object{
        @Volatile
        private var INSTANCE: AbsenDatabase?=null

        fun getInstance(context: Context): AbsenDatabase {  if (INSTANCE == null) {
            synchronized(GuruDatabase::class.java) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AbsenDatabase::class.java, "monitoring.db")
                    .build()
            }
        }
            return INSTANCE as AbsenDatabase
        }

    }
}