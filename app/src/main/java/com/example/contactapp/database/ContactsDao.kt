package com.example.contactapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.contactapp.data.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    //get all contacts by category
    @Query("SELECT * FROM contacts_table WHERE contact_category LIKE :search ")
    fun findUserWithCategory(search: String): Flow<List<Contact>>

    //insert contact
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Query("DELETE FROM contacts_table")
    suspend fun deleteAll()
}