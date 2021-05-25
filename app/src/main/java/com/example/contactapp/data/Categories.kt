package com.example.contactapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Categories(
    val name: String,
    val image: Int,
    val color:String
): Parcelable