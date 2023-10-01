package com.example.hw3_contacts

import android.graphics.Color
import android.widget.AdapterView.OnItemClickListener
import com.bumptech.glide.Glide
import com.example.hw3_contacts.data.DelegateAdapterItem
import com.example.hw3_contacts.data.PersonDOB
import com.example.hw3_contacts.data.PersonDTO
import com.example.hw3_contacts.data.PersonNoDOB
import com.example.hw3_contacts.databinding.ItemPersonBinding
import com.example.hw3_contacts.databinding.ItemPersonNoDobBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun dobDelegate() = adapterDelegateViewBinding<PersonDOB, DelegateAdapterItem, ItemPersonBinding >(
    {layoutInflater, root -> ItemPersonBinding.inflate(layoutInflater, root, false) }
) {
    var isSelected = false

    binding.root.setOnClickListener {
        if (!isSelected) binding.root.setBackgroundColor(Color.BLUE)
        else binding.root.setBackgroundColor(Color.WHITE)
        isSelected = !isSelected
    }



    bind {
        binding.personName.text = "${item.firstName} ${item.lastName}"
        binding.phoneNumber.text = item.phone.toString()
        Glide.with(binding.root.context)
            .load(item.picture)
            .into(binding.personPhoto)
        binding.dob.text = item.dob
    }

}

fun noDobDelegate() = adapterDelegateViewBinding<PersonNoDOB, DelegateAdapterItem, ItemPersonNoDobBinding >(
    {layoutInflater, root -> ItemPersonNoDobBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        binding.personName.text = "${item.firstName} ${item.lastName}"
        binding.phoneNumber.text = item.phone.toString()
        Glide.with(binding.root.context)
            .load(item.picture)
            .into(binding.personPhoto)
    }
}