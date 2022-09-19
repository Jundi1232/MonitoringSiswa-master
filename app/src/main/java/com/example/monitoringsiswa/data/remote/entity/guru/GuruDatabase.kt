package com.example.monitoringsiswa.data.remote.entity.guru

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel
import com.example.monitoringsiswa.data.remote.entity.mapel.MapelDao
import com.example.monitoringsiswa.data.remote.entity.siswa.SiswaDatabase
import java.util.concurrent.Executors

@Database(entities = [Guru::class,Mapel::class], version = 1)
abstract class GuruDatabase:RoomDatabase() {
    abstract fun guruDao(): GuruDao

    companion object{
        @Volatile
        private var INSTANCE:GuruDatabase?=null


        fun getInstance(context: Context): GuruDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GuruDatabase::class.java,
                    "monitoring1.db"
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