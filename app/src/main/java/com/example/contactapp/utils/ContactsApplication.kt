package com.example.contactapp.utils

import android.app.Application
import com.example.contactapp.database.ContactsDatabase
import com.example.contactapp.repository.ContactsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ContactsApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ContactsDatabase.getDatabase(this , applicationScope) }
    val repository by lazy { ContactsRepository(database.contactDao() , "family") }
}