package com.example.contactapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.contactapp.data.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Contact::class] , version = 1 , exportSchema = false)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactDao() : ContactsDao


    companion object {

        @Volatile
        private var INSTANCE: ContactsDatabase? = null

        fun getDatabase(context: Context ,  scope : CoroutineScope) : ContactsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactsDatabase::class.java,
                    "contacts_database"
                ).addCallback(ContactsCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }


    private class ContactsCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.contactDao())
                }
            }
        }

        suspend fun populateDatabase(contactsDao: ContactsDao) {
            // Delete all content here.
            contactsDao.deleteAll()

            // Add sample contacts.
            var contact = Contact(1 , "Sample Number" , "0722000000","family")
            contactsDao.insertContact(contact)
            // TODO: Add your own words!
        }

    }


}