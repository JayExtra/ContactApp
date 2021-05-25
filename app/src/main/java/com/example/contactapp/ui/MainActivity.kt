package com.example.contactapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contactapp.R
import com.example.contactapp.adapters.ContactsCategoryRecyclerAdapter
import com.example.contactapp.data.Categories
import com.example.contactapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val categories = mutableListOf<Categories>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        categories.add(Categories("family" , R.drawable.ic_family, "RED"))
        categories.add(Categories("friends" , R.drawable.ic_friends , "BLUE"))
        categories.add(Categories("work" , R.drawable.ic_work , "GREEN"))
        categories.add(Categories("tutors" , R.drawable.ic_tutors , "YELLOW"))

        val categoryAdapter = ContactsCategoryRecyclerAdapter(this@MainActivity)
        categoryAdapter.createCategories(categories)

        binding.apply {

            contactCategoryRecyclerView.apply {
                adapter = categoryAdapter
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                setHasFixedSize(true)
            }


        }
    }
}