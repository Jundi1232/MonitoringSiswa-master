package com.example.monitoringsiswa.data.remote.entity.mapel

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity(tableName = "mapel")
data class Mapel(
    @ColumnInfo(name = "idmapel")
    @NonNull
    @PrimaryKey
    val idmapel : String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "name_mapel")
    val namemapel : String?,

    @ColumnInfo(name = "kkm")
    val kkm : Float?
    ): Parcelable
