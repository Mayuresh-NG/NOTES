package com.example.notes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: String,
    var title: String,
    var description: String
) : Parcelable
