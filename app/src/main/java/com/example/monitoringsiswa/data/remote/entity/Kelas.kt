package com.example.monitoringsiswa.data.remote.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID
@Parcelize
data class Kelas(
    val idkelas : String?,val kelas : String?,val nip : String?
    ):Parcelable