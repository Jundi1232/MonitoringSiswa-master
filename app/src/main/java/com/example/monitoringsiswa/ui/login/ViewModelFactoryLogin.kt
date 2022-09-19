package com.example.monitoringsiswa.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.monitoringsiswa.data.remote.entity.login.LoginRepository
import com.jn.latihan1.ui.login.LoginViewModel

class ViewModelFactoryLogin private constructor(private val loginRepository: LoginRepository):
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryLogin? = null

        fun getInstance(context: Context): ViewModelFactoryLogin =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryLogin(
                    LoginRepository.getInstance(context)
                )
            }
    }

}