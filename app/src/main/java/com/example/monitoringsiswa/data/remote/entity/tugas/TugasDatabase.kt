package com.example.monitoringsiswa.data.remote.entity.tugas

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.monitoringsiswa.data.remote.entity.siswa.SiswaDao
import com.example.monitoringsiswa.data.remote.entity.siswa.SiswaDatabase
import java.util.concurrent.Executors


abstract class TugasDatabase {
    abstract fun msiswaDao(): SiswaDao

    companion object{
        @Volatile
        private var INSTANCE: SiswaDatabase?=null

        fun getInstance(context: Context): SiswaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SiswaDatabase::class.java,
                    "task.db"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        INSTANCE?.let { database ->
                            Executors.newSingleThreadScheduledExecutor().execute {
                            }
                        }
                    }
                })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}