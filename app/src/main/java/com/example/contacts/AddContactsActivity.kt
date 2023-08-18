package com.example.contacts

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.contacts.databinding.ActivityAddContactsBinding


class AddContactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddContactsBinding
    private lateinit var contactsDao: ContactsDao
    private lateinit var database :ContactsDatabase
    private lateinit var contact: Contacts


     private var receivedValue: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database=ContactsDatabase.getInstance(this)
        contactsDao=database.contactsDao()

        receivedValue= intent.getIntExtra("ID",0)


        if(receivedValue!=0){
            contact=contactsDao.getById(receivedValue)
            binding.EditFName.setText(contact.fname)
            binding.EditLName.setText(contact.lname)
            binding.EditEmail.setText(contact.email)
            binding.EditPhoneNumber.setText(contact.mob_num)
        }
        binding.CancelContact.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.SaveContact.setOnClickListener {
            if(receivedValue==0){
                if(binding.EditFName.length()!=0&&binding.EditLName.length()!=0&&
                    binding.EditEmail.length()!=0&&binding.EditPhoneNumber.length()!=0){
                    if(Validation.isEmailValid(binding.EditEmail.text.toString())&&
                        Validation.isPhoneNumberValid(binding.EditPhoneNumber.text.toString())){
                        val contacts = Contacts(fname = binding.EditFName.text.toString(),
                            lname = binding.EditLName.text.toString(),
                            email = binding.EditEmail.text.toString(), mob_num = binding.EditPhoneNumber.text.toString())

                        contactsDao.insertContacts(contacts)


                        Toast.makeText(this,"Contact added successfully",Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Please enter valid email id and mobile number",Toast.LENGTH_LONG).show()

                    }


                }else{
                    Toast.makeText(this,"Please enter all the fields",Toast.LENGTH_LONG).show()

                }
            }else{
                if(binding.EditFName.length()!=0&&binding.EditLName.length()!=0&&
                    binding.EditEmail.length()!=0&&binding.EditPhoneNumber.length()!=0){
                    if(Validation.isEmailValid(binding.EditEmail.text.toString())&&
                        Validation.isPhoneNumberValid(binding.EditPhoneNumber.text.toString())){

                        contactsDao.updateUserById(receivedValue,binding.EditFName.text.toString(),
                            binding.EditLName.text.toString(),binding.EditEmail.text.toString(),
                            binding.EditPhoneNumber.text.toString())


                        Toast.makeText(this,"Contact updated successfully",Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Please enter valid email id and mobile number",Toast.LENGTH_LONG).show()

                    }

                }else{
                    Toast.makeText(this,"Please enter all the fields",Toast.LENGTH_LONG).show()

                }
            }


        }

        binding.deleteContacts.setOnClickListener {
            deletePopup()
        }
    }

    private fun deletePopup() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.delete_dialog)

        val yesButton = dialog.findViewById<Button>(R.id.yes_button)
        val noButton = dialog.findViewById<Button>(R.id.no_button)

        yesButton.setOnClickListener {

            dialog.dismiss()
            if(receivedValue!=0){
                contactsDao.deleteById(receivedValue)
                Toast.makeText(this,"Contact deleted successfully",Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        noButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    override fun onBackPressed() {

    }


}