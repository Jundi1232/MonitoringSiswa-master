package com.example.monitoringsiswa.ui.admin.mapel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.monitoringsiswa.data.remote.entity.mapel.MapelRepository

class ViewModelFactory private  constructor(private  val mapelRepository: MapelRepository):ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MapelViewModel::class.java) -> {
                MapelViewModel(mapelRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    MapelRepository.getInstance(context)
                )
            }
    }
}