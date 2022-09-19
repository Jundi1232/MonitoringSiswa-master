package com.example.monitoringsiswa.data.remote.entity.login

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "login")
data class Login(
    @ColumnInfo(name = "username")
    @NonNull
    @PrimaryKey
    var username: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "level")
    var level: String,
):Parcelable
