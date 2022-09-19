package com.example.monitoringsiswa.ui.admin.walmur

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.monitoringsiswa.data.remote.entity.walmur.WalMurRepository

class ViewModelFactoryWalmur private  constructor(private  val walMurRepository: WalMurRepository):ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(WalMurViewModel::class.java) -> {
                WalMurViewModel(walMurRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryWalmur? = null

        fun getInstance(context: Context): ViewModelFactoryWalmur =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryWalmur(
                    WalMurRepository.getInstance(context)
                )
            }
    }
}