package com.example.contacts

import androidx.room.*

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contacts_table")
    fun getAllContacts(): List<Contacts>

    @Query("SELECT * FROM contacts_table WHERE id = :id")
    fun getById(id: Int): Contacts

    @Insert
    fun insertContacts(contacts: Contacts)

    @Query("DELETE FROM contacts_table WHERE id = :id")
    fun deleteById(id: Int)

    @Query("UPDATE contacts_table SET fname = :fname, lname = :lname, email = :email, mob_num = :mob_num WHERE id = :id")
    fun updateUserById(id: Int, fname: String, lname: String, email: String, mob_num: String)
}