package com.example.monitoringsiswa.ui.admin.absensi

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.monitoringsiswa.data.remote.entity.absensi.AbsenRepository
import com.example.monitoringsiswa.data.remote.entity.siswa.SiswaRepository

class ViewModelFactoryAbsen private  constructor(private  val absenRepository: AbsenRepository,
                                                 private val siswaRepository: SiswaRepository,
                                                ):ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(AbsenViewModel::class.java) -> {
                AbsenViewModel(absenRepository,siswaRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryAbsen? = null

        fun getInstance(context: Context): ViewModelFactoryAbsen =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryAbsen(
                    AbsenRepository.getInstance(context),
                    SiswaRepository.getInstance(context)
                )
            }
    }
}