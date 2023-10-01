package com.example.hw3_contacts.data

data class PersonDOB(
    override val id: Int,
    val picture: String,
    val firstName: String,
    override val lastName: String,
    val phone: String,
    val dob: String
) : DelegateAdapterItem

data class PersonNoDOB(
    override val id: Int,
    val picture: String,
    val firstName: String,
    override val lastName: String,
    val phone: String
) : DelegateAdapterItem

object Util {
    fun makePersonList(list: List<PersonDTO>): MutableList<DelegateAdapterItem> {
        val newList = mutableListOf<DelegateAdapterItem>()
        list.forEachIndexed { index, element ->
            if (element.dob != null) {
                newList.add(
                    PersonDOB(
                        id = index,
                        picture = element.picture.mediumPic,
                        firstName = element.name.firstName,
                        lastName = element.name.lastName,
                        phone = element.phone,
                        dob = element.dob.date
                    )
                )
            } else {
                newList.add(
                    PersonNoDOB(
                        id = index,
                        picture = element.picture.mediumPic,
                        firstName = element.name.firstName,
                        lastName = element.name.lastName,
                        phone = element.phone
                    )
                )
            }
        }
        return newList
    }
}