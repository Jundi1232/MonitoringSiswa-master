package com.example.monitoringsiswa.data.remote.entity.login

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Login::class], version = 1)
abstract class LoginDatabase: RoomDatabase() {
    abstract fun loginDao(): LoginDao

    companion object{
        @Volatile
        private var INSTANCE:LoginDatabase?=null

        fun getInstance(context: Context):LoginDatabase{  if (INSTANCE == null) {
            synchronized(LoginDatabase::class.java) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    LoginDatabase::class.java, "profile.db")
                    .build()
            }
        }
            return INSTANCE as LoginDatabase
        }

    }
}