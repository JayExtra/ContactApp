package com.example.contactapp.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.data.Categories
import com.example.contactapp.data.Contact
import com.example.contactapp.databinding.ActivityMainBinding
import com.example.contactapp.databinding.SingleMenuItemBinding
import com.example.contactapp.ui.ContactActivity
import com.google.android.material.snackbar.Snackbar

class ContactsCategoryRecyclerAdapter(private val context : Context) : RecyclerView.Adapter<ContactsCategoryRecyclerAdapter.CategoryViewHolder>()  {
    private val categories = mutableListOf<Categories>()

    fun createCategories(category: List<Categories>){
        this.categories.addAll(category)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(private val binding: SingleMenuItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(category : Categories){
            binding.apply {
                categoryText.text = category.name
                categoryImage.setImageResource(category.image)

                if (category.color == "RED"){
                    menuCard.setCardBackgroundColor(RED)
                }
                else if (category.color == "YELLOW"){
                    menuCard.setCardBackgroundColor(YELLOW)
                }
                else if (category.color == "GREEN"){
                    menuCard.setCardBackgroundColor(GREEN)
                }
                else {
                    menuCard.setCardBackgroundColor(BLUE)
                }

                menuCard.setOnClickListener {
                    Snackbar.make(it,"${category.name} selected" , Snackbar.LENGTH_SHORT).show()
                    val intent = Intent(context , ContactActivity::class.java)
                    intent.putExtra("TITLE" , category.name)
                   startActivity(context,intent,null)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = SingleMenuItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = categories[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return categories.size
    }


}