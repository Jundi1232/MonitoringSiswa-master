package com.example.monitoringsiswa.data.remote.entity.login

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDao {
    @Query("Select * From login where username= :user and password=:password")
    fun getUser(user:String, password:String): LiveData<Login>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(login: Login)
}

