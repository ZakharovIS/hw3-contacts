package com.example.hw3_contacts

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.hw3_contacts.data.DelegateAdapterItem
import com.example.hw3_contacts.data.PersonDOB
import com.example.hw3_contacts.data.PersonNoDOB
import com.example.hw3_contacts.data.PersonRepository
import com.example.hw3_contacts.databinding.FragmentContactsListBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactsListFragment : Fragment() {

    private val personRepository = PersonRepository()
    private val _isLoading = MutableStateFlow(false)
    lateinit var personList: MutableList<DelegateAdapterItem>
    val adapter = ListDelegationAdapter(
        dobDelegate(onClick = { item, pos -> onClick(item, pos) }),
        noDobDelegate(onClick = { item, pos -> onClick(item, pos) })
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
                        val dialog = Dialog(requireContext())
                        dialog.setContentView(R.layout.add_update_contact)
                        dialog.show()
                        var dob = dialog.findViewById<EditText>(R.id.edit_dob)
                        dialog.findViewById<Button>(R.id.edit_btn).setOnClickListener {
                            if (dob.text != null) {
                                var person = PersonDOB(
                                    id = personList.last().id + 1,
                                    firstName = dialog.findViewById<EditText>(R.id.edit_firstName).text.toString(),
                                    lastName = dialog.findViewById<EditText>(R.id.edit_lastName).text.toString(),
                                    phone = dialog.findViewById<EditText>(R.id.edit_phone).text.toString(),
                                    dob = dialog.findViewById<EditText>(R.id.edit_dob).text.toString(),
                                    picture = "https://randomuser.me/api/portraits/med/men/75.jpg"
                                )
                                personList.add(person)
                                dialog.cancel()
                            } else {
                                var person = PersonNoDOB(
                                    id = personList.last().id + 1,
                                    firstName = dialog.findViewById<EditText>(R.id.edit_firstName).text.toString(),
                                    lastName = dialog.findViewById<EditText>(R.id.edit_lastName).text.toString(),
                                    phone = dialog.findViewById<EditText>(R.id.edit_phone).text.toString(),
                                    picture = "https://randomuser.me/api/portraits/med/men/75.jpg"
                                )
                                personList.add(person)
                                dialog.cancel()
                            }

                        }
                    }

                    R.id.edit -> {
                        if (personList.count { it.isSelected } == 1) {
                            val dialog = Dialog(requireContext())
                            val person = personList.first { it.isSelected }
                            dialog.setContentView(R.layout.add_update_contact)
                            dialog.findViewById<TextView>(R.id.title_edit_frame).text =
                                "Редактировать контакт"
                            dialog.findViewById<EditText>(R.id.edit_firstName)
                                .setText(person.firstName)
                            dialog.findViewById<EditText>(R.id.edit_lastName)
                                .setText(person.lastName)
                            dialog.findViewById<EditText>(R.id.edit_phone).isEnabled = false
                            dialog.findViewById<EditText>(R.id.edit_dob).isEnabled = false
                            dialog.show()
                            dialog.findViewById<Button>(R.id.edit_btn).setOnClickListener {
                                personList.first { it.isSelected }.firstName =
                                    dialog.findViewById<EditText>(R.id.edit_firstName).text.toString()
                                personList.first { it.isSelected }.lastName =
                                    dialog.findViewById<EditText>(R.id.edit_lastName).text.toString()
                                adapter.notifyDataSetChanged()
                                dialog.cancel()
                            }
                        } else Toast.makeText(context, "Выделите один контакт", Toast.LENGTH_SHORT)
                            .show()

                    }

                    R.id.delete -> {
                        personList.removeAll { it.isSelected }
                        adapter.notifyDataSetChanged()
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

        }
    }

    private fun onClick(person: DelegateAdapterItem, position: Int) {

        /*if(personList.contains(person)) Log.d("123", "All good")
            adapter.notifyItemChanged(position)*/
        /*if (selectedPersonsList.contains(person)) {
                selectedPersonsList.remove(person)

            }
            else selectedPersonsList.add(person)*/
    }

}