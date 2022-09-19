package com.example.monitoringsiswa.ui.admin.guru

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.monitoringsiswa.data.remote.entity.guru.GuruRepository
import com.example.monitoringsiswa.data.remote.entity.login.LoginRepository
import com.example.monitoringsiswa.data.remote.entity.mapel.MapelRepository

class ViewModelFactoryguru private  constructor(private  val guruRepository: GuruRepository,
                                                private val mapelRepository: MapelRepository,
                                                private val loginRepository: LoginRepository):ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(GuruViewModel::class.java) -> {
                GuruViewModel(guruRepository,mapelRepository, loginRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryguru? = null

        fun getInstance(context: Context): ViewModelFactoryguru =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryguru(
                    GuruRepository.getInstance(context),
                    MapelRepository.getInstance(context),
                    LoginRepository.getInstance(context)
                )
            }
    }
}