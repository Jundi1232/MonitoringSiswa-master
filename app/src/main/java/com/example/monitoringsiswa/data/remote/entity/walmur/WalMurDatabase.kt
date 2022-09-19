package com.example.monitoringsiswa.data.remote.entity.walmur

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.monitoringsiswa.data.remote.entity.guru.GuruDatabase
import java.util.concurrent.Executors

@Database(entities = [WaliMurid::class], version = 2, exportSchema = false)
abstract class WalMurDatabase:RoomDatabase() {
    abstract fun walmurDao(): WalMurDao

    companion object{
        @Volatile
        private var INSTANCE: WalMurDatabase?=null

        fun getInstance(context: Context): WalMurDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WalMurDatabase::class.java,
                    "monitoring8.db"
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