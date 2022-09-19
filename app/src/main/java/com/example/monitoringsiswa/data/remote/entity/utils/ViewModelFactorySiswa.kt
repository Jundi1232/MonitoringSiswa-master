package com.example.monitoringsiswa.data.remote.entity.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.monitoringsiswa.data.remote.entity.login.LoginRepository
import com.example.monitoringsiswa.data.remote.entity.siswa.SiswaRepository
import com.example.monitoringsiswa.ui.admin.siswa.SiswaViewModel

class ViewModelFactorySiswa private  constructor(private  val siswaRepository: SiswaRepository,private val loginRepository: LoginRepository):ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(SiswaViewModel::class.java) -> {
                SiswaViewModel(siswaRepository,loginRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    companion object {
        @Volatile
        private var instance: ViewModelFactorySiswa? = null

        fun getInstance(context: Context): ViewModelFactorySiswa =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactorySiswa(
                    SiswaRepository.getInstance(context),
                    LoginRepository.getInstance(context)
                )
            }
    }

}