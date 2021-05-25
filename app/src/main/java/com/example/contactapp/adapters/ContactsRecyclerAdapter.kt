package com.example.contactapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.data.Contact
import com.example.contactapp.databinding.SingleContactItemBinding

class ContactsRecyclerAdapter : ListAdapter<Contact , ContactsRecyclerAdapter.ContactsViewHolder>(ContactsCallback())  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
       val binding = SingleContactItemBinding.inflate(LayoutInflater.from(parent.context)
       ,parent , false)

        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
      val currentItem = getItem(position)
        holder.bind(currentItem)
    }
    inner class ContactsViewHolder(private val binding: SingleContactItemBinding) :
            RecyclerView.ViewHolder(binding.root){

                fun bind(contact: Contact){

                    binding.apply {
                        textViewName.text = contact.name
                        textViewNumber.text = contact.number
                    }

                }

            }


    class ContactsCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }
    }




}