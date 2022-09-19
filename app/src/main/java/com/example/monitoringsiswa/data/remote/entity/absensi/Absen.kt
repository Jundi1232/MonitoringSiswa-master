package com.example.monitoringsiswa.data.remote.entity.absensi

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date
import java.util.UUID

@Parcelize
@Entity(tableName = "absen")
data class Absen(
    @ColumnInfo(name = "idabsensi")
    @PrimaryKey
    val idabsensi : String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "nis")
    val nis : String,

    @ColumnInfo(name = "tgl")
    val tgl: String,

    @ColumnInfo(name = "ket")
    val ket: String,

    @ColumnInfo(name = "nama_mapel")
    val nama_mapel : String,

    @ColumnInfo(name = "idkelas")
    val idkelas : String
    ):Parcelable
