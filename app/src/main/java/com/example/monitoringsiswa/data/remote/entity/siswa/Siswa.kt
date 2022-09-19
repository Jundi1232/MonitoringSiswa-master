package com.example.monitoringsiswa.data.remote.entity.siswa

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "siswa")
data class Siswa (
    @ColumnInfo(name = "nis")
    @NonNull
    @PrimaryKey
    var nis : String,

    @ColumnInfo(name = "nama")
    var nama: String,

    @ColumnInfo(name = "jk:")
    var jk: String,

    @ColumnInfo(name = "tempat")
    var tempat : String?,

    @ColumnInfo(name = "tglLahir")
    var tglLahir : Long,

    @ColumnInfo(name = "kelas")
    var kelas : String?,


    @ColumnInfo(name = "alamat")
    var alamat : String?,
    ): Parcelable