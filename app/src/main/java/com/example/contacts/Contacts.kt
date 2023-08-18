package com.example.contacts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contacts_table")
data class Contacts(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "fname") val fname: String,
    @ColumnInfo(name = "lname") val lname: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "mob_num") val mob_num: String
)
