package com.example.monitoringsiswa.data.remote.entity.walmur

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "walmur")
data class WaliMurid(
    @ColumnInfo(name = "nik")
    @NonNull
    @PrimaryKey
    var nik : String,

    @ColumnInfo(name = "nama")
    var nama: String,

    @ColumnInfo(name = "jk")
    var jk : String,

    @ColumnInfo(name = "status")
    var status : String?,

    @ColumnInfo(name = "nis")
    var nis : String,

    @ColumnInfo(name = "telepon")
    var telepon : String
):Parcelable