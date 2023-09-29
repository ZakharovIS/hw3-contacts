package com.example.hw3_contacts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hw3_contacts.databinding.FragmentContactsListBinding

class ContactsListFragment : Fragment() {

    companion object {
        fun newInstance() = ContactsListFragment()
    }

    //private lateinit var viewModel: ContactsListViewModel
    private var binding: FragmentContactsListBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsListBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}