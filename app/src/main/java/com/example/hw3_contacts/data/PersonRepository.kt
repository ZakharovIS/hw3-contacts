package com.example.hw3_contacts.data

import com.example.hw3_contacts.api.RetrofitServices

class PersonRepository {

    suspend fun getPersons(): MutableList<DelegateAdapterItem> {
        val personDTOList: MutableList<PersonDTO> =
            RetrofitServices.randomPersonApi.getPersonsNoDOB().body()!!.results.toMutableList()
        personDTOList.addAll(
            RetrofitServices.randomPersonApi.getPersonsWithDOB().body()!!.results.toMutableList()
        )
        personDTOList.shuffle()
        return Util.makePersonList(personDTOList)
    }
}