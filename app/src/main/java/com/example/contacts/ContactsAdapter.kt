package com.example.contacts

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.ContactLayoutBinding

class ContactsAdapter(var contactsList: List<Contacts>,private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>(){


    class ViewHolder(val binding: ContactLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ContactLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.name.text = "Name: "+contactsList[position].fname.substring(0, 1).toUpperCase()+contactsList[position].fname.substring(1)+" "+contactsList[position].lname.substring(0, 1).toUpperCase()+contactsList[position].lname.substring(1)
       holder.binding.email.text = "Email: "+contactsList[position].email
       holder.binding.phone.text = "Phone: "+contactsList[position].mob_num

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(contactsList[position].id)
            Log.e("adapter","pos: "+position)
        }
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }
}
