package com.example.monitoringsiswa.data.remote.entity.tugas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Tugas(
    val idtugas : String,
    val idkelas : String,
    val idmapel : String,
    val tglDeadline: Date?,
    val keterangan: String
):Parcelable
