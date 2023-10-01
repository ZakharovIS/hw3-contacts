package com.example.hw3_contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.lifecycleScope
import com.example.hw3_contacts.data.DelegateAdapterItem
import com.example.hw3_contacts.data.PersonDOB
import com.example.hw3_contacts.data.PersonDTO
import com.example.hw3_contacts.data.PersonRepository
import com.example.hw3_contacts.databinding.FragmentContactsListBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactsListFragment : Fragment() {

    private val personRepository = PersonRepository()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    lateinit var personList: MutableList<DelegateAdapterItem>
    val adapter = ListDelegationAdapter(
        dobDelegate(),
        noDobDelegate()
    )


    companion object {
        fun newInstance() = ContactsListFragment()
    }


    private var binding: FragmentContactsListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsListBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_add_edit_delete, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {

                    R.id.add -> {
                        Toast.makeText(context, "add", Toast.LENGTH_SHORT).show()
                    }
                    R.id.edit -> {
                        Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show()
                    }
                    R.id.delete -> {
                        Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()
                    }

                }

                return true
            }

        }, viewLifecycleOwner)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun loadData() {
        lifecycleScope.launch {
            _isLoading.value = true
            personList = personRepository.getPersons()
            _isLoading.value = false
            Log.d("123", "$personList")
            adapter.items = personList
            binding!!.RVContacts.adapter = adapter

            /*personList.add(PersonDOB(
                id = 1024,
                picture = "https://randomuser.me/api/portraits/med/men/35.jpg",
                firstName = "Igor",
                lastName = "Zakharov",
                phone = "424242",
                dob = "06/06/1983"
            ))*/
        }
    }



}