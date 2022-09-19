package com.example.monitoringsiswa.data.remote.entity.siswa

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.monitoringsiswa.data.remote.entity.absensi.Absen
import com.example.monitoringsiswa.data.remote.entity.mapel.MapelDao
import com.example.monitoringsiswa.data.remote.entity.mapel.MapelDatabase
import java.util.concurrent.Executors

@Database(entities = [Siswa::class], version = 1)
abstract class SiswaDatabase:RoomDatabase() {
    abstract fun siswaDao(): SiswaDao

    companion object{
        @Volatile
        private var INSTANCE: SiswaDatabase?=null

        fun getInstance(context: Context): SiswaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SiswaDatabase::class.java,
                    "siswa.db"
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