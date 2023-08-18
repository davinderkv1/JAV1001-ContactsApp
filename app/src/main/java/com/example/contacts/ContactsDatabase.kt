package com.example.contacts

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [(Contacts::class)], version = 1, exportSchema = false)
abstract class ContactsDatabase: RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
    companion object {

        @Volatile
        private var INSTANCE: ContactsDatabase? = null

        fun getInstance(context: Context) : ContactsDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, ContactsDatabase::class.java, "CONTACTS_DATABASE")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build()


                return INSTANCE!!

            }
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

            }
        }

    }
}


