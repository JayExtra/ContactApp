package com.example.contactapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "contacts_table")
@Parcelize
data class Contact (
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val name : String?,
    val number : String?,
    val contact_category : String?): Parcelable