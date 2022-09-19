package com.jn.latihan1.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.monitoringsiswa.data.remote.entity.login.Login
import com.example.monitoringsiswa.data.remote.entity.login.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository): ViewModel() {
    fun getUser(username:String, password:String): LiveData<Login> = loginRepository.getUser(username,password)
}