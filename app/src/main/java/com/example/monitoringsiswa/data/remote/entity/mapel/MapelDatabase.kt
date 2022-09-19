package com.example.monitoringsiswa.data.remote.entity.mapel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Mapel::class], version = 1)
abstract class MapelDatabase:RoomDatabase() {
    abstract fun mapelDao(): MapelDao

    companion object{
        @Volatile
        private var INSTANCE: MapelDatabase?=null

        fun getInstance(context: Context): MapelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MapelDatabase::class.java,
                    "monitoring.db"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}