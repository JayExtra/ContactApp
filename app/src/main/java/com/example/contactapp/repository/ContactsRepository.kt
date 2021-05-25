package com.example.contactapp.repository

import com.example.contactapp.data.Contact
import com.example.contactapp.database.ContactsDao
import kotlinx.coroutines.flow.Flow

class ContactsRepository(
    private val contactDao : ContactsDao,
    category : String
    ){


    val allContacts : Flow<List<Contact>> = contactDao.findUserWithCategory(category)

    suspend fun insert(contact: Contact) {
        contactDao.insertContact(contact)
    }
}
