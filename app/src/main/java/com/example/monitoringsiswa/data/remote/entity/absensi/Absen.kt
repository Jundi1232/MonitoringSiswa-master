package com.example.monitoringsiswa.data.remote.entity.absensi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "absen")
data class Absen(
    @ColumnInfo(name = "idabsensi")
    @PrimaryKey
    val idabsensi : String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "nis")
    val nis : String,

    @ColumnInfo(name = "tgl")
    val tgl: Long,

    @ColumnInfo(name = "ket")
    val ket: String,

    @ColumnInfo(name = "nama_mapel")
    val nama_mapel : String,

    @ColumnInfo(name = "idkelas")
    val idkelas : String
    )
