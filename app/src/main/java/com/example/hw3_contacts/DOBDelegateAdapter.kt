package com.example.hw3_contacts

import android.graphics.Color
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.hw3_contacts.data.DelegateAdapterItem
import com.example.hw3_contacts.data.PersonDOB
import com.example.hw3_contacts.data.PersonNoDOB
import com.example.hw3_contacts.databinding.ItemPersonBinding
import com.example.hw3_contacts.databinding.ItemPersonNoDobBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun dobDelegate(onClick: (DelegateAdapterItem, Int) -> Unit) =
    adapterDelegateViewBinding<PersonDOB, DelegateAdapterItem, ItemPersonBinding>(
        { layoutInflater, root -> ItemPersonBinding.inflate(layoutInflater, root, false) }
    ) {

        binding.root.setOnClickListener {
            item.isSelected = !item.isSelected
            //onClick(item, layoutPosition)
            this.bindingAdapter!!.notifyItemChanged(layoutPosition)
        }



        bind {
            binding.personName.text = "${item.firstName} ${item.lastName}"
            binding.phoneNumber.text = item.phone.toString()
            Glide.with(binding.root.context)
                .load(item.picture)
                .into(binding.personPhoto)
            binding.dob.text = item.dob
            if (item.isSelected) binding.root.setBackgroundColor(Color.DKGRAY)
            else binding.root.setBackgroundColor(Color.WHITE)
        }

    }

fun noDobDelegate(onClick: (DelegateAdapterItem, Int) -> Unit) =
    adapterDelegateViewBinding<PersonNoDOB, DelegateAdapterItem, ItemPersonNoDobBinding>(
        { layoutInflater, root -> ItemPersonNoDobBinding.inflate(layoutInflater, root, false) }
    ) {

        binding.root.setOnClickListener {
            item.isSelected = !item.isSelected
            //onClick(item, layoutPosition)
            this.bindingAdapter!!.notifyItemChanged(layoutPosition)
        }

        bind {
            binding.personName.text = "${item.firstName} ${item.lastName}"
            binding.phoneNumber.text = item.phone.toString()
            Glide.with(binding.root.context)
                .load(item.picture)
                .into(binding.personPhoto)
            if (item.isSelected) binding.root.setBackgroundColor(Color.DKGRAY)
            else binding.root.setBackgroundColor(Color.WHITE)
        }


    }

class ItemDiffCallback : DiffUtil.ItemCallback<DelegateAdapterItem>() {
    override fun areItemsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean {
        return oldItem.equals(newItem)
    }

    override fun getChangePayload(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Any? {
        return if (oldItem.firstName != newItem.firstName ||
            oldItem.lastName != newItem.lastName
        ) true else null
    }
}