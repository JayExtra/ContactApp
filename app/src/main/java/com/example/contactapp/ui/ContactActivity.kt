package com.example.contactapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapp.R
import com.example.contactapp.adapters.ContactsRecyclerAdapter
import com.example.contactapp.data.Contact
import com.example.contactapp.databinding.ActivityContactBinding
import com.example.contactapp.databinding.ActivityMainBinding
import com.example.contactapp.utils.ContactsApplication
import com.example.contactapp.viewmodels.ContactsViewModel
import com.example.contactapp.viewmodels.ContactsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.add_contact_dialog.view.*

class ContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactBinding
    private var _title : String? = null

    private val contactsViewModel: ContactsViewModel by viewModels {
        ContactsViewModelFactory((application as ContactsApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        prepareToolbar()

        retrieveContacts()

    }

    fun retrieveContacts(){

        val contactsAdapter = ContactsRecyclerAdapter()
        binding.apply {
            recyclerViewContacts.apply {
                adapter = contactsAdapter
                layoutManager = LinearLayoutManager(this@ContactActivity)
            }

            fabAddContact.setOnClickListener {
                launchDialog()
            }

        }

        contactsViewModel.allContacts.observe(this, Observer { contacts ->
            // Update the cached copy of the contacts in the adapter.
            contacts?.let { contactsAdapter.submitList(it) }
        })

    }

    fun prepareToolbar() {

        //grab passed intent , in this case the title of the category
        val intent = intent.extras ?: return
        val title = intent.getString("TITLE")
        _title = title


        //to change title of activity
        val actionBar = supportActionBar
        actionBar!!.title = title
    }

    fun launchDialog(){

      val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_contact_dialog,
      null)

        val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)


        val mAlertDialog = mBuilder.show()


        mDialogView.savebtn.setOnClickListener {
            //mAlertDialog.dismiss()
            val name = mDialogView.nameEditText.text.toString()
            val number = mDialogView.numberEditText.text.toString()

           // Snackbar.make(it , "Details: $name,$number", Snackbar.LENGTH_SHORT).show()

            val contacts = Contact(
                    name = name,
                    number = number,
                    contact_category = _title
            )
            contactsViewModel.insert(contacts)

            mAlertDialog.dismiss()


        }

    }
}