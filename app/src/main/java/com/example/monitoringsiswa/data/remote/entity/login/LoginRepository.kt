package com.example.monitoringsiswa.data.remote.entity.login

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LoginRepository(private val dao: LoginDao) {
    suspend  fun insert(login: Login) {
        return dao.insert(login)
    }

        fun getUser(usernae:String,password:String):LiveData<Login>{
            return dao.getUser(usernae,password)
        }

    companion object{
        @Volatile
        private var instance: LoginRepository?=null

        fun getInstance(context: Context): LoginRepository {
            return instance ?: synchronized(this){
                if (instance == null) {
                    val database = LoginDatabase.getInstance(context)
                    instance = LoginRepository(
                        database.loginDao(),

                    )
                }
                return instance as LoginRepository
            }

        }
    }


}