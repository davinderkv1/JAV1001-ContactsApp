package com.example.contacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(),OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactsDao: ContactsDao
    private lateinit var database :ContactsDatabase
    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var contactsList: List<Contacts>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database=ContactsDatabase.getInstance(this)
        contactsDao=database.contactsDao()
        contactsList= listOf<Contacts>()


            contactsList=contactsDao.getAllContacts()
            Log.e("app","contacts"+contactsDao.getAllContacts().size)

            if(contactsList.isEmpty()){
                binding.contactsList.visibility= View.GONE
                binding.textView.visibility= View.VISIBLE

            }else{
                binding.contactsList.visibility= View.VISIBLE
                binding.textView.visibility= View.GONE
                contactsAdapter = ContactsAdapter(contactsList,this)
                val layoutManager = LinearLayoutManager(applicationContext)
                binding.contactsList.layoutManager = layoutManager
                binding.contactsList.adapter = contactsAdapter
            }

        val themeSwitch = binding.themeSwitch
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Switch to dark theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                // Switch to light theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


        binding.addContacts.setOnClickListener {
            val intent = Intent(this, AddContactsActivity::class.java)
            intent.putExtra("ID",0)
            startActivity(intent)
        }
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(this, AddContactsActivity::class.java)
        intent.putExtra("ID",id)
        startActivity(intent)
    }
}