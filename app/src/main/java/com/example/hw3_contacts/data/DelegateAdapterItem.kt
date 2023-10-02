package com.example.hw3_contacts.data

abstract class DelegateAdapterItem {
    abstract val id: Int
    abstract var firstName: String
    abstract var lastName: String
    var isSelected: Boolean = false
}


