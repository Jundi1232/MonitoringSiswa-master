package com.example.monitoringsiswa.data.remote.entity.guru

import android.os.Parcelable
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "guru")
data class Guru(
    @ColumnInfo(name = "nip")
    @NonNull
    @PrimaryKey
    var nip: String,

    @ColumnInfo(name = "nama")
    var nama: String,

    @ColumnInfo(name = "mapel")
    var mapel:String,

    @ColumnInfo(name = "jk")
    var jk: String,

    @ColumnInfo(name = "tempat")
    var tempat: String,

    @ColumnInfo(name = "tglLahir")
    var tglLahir: Long,

    @ColumnInfo(name = "alamat")
    var alamat: String,

    @ColumnInfo(name = "telepon")
    var telepon: String
):Parcelable