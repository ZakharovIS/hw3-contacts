package com.example.hw3_contacts.data

data class PersonDOB(
    override val id: Int,
    override var firstName: String,
    override var lastName: String,
    val picture: String,
    val phone: String,
    val dob: String
) : DelegateAdapterItem()

data class PersonNoDOB(
    override val id: Int,
    override var firstName: String,
    override var lastName: String,
    val picture: String,
    val phone: String
) : DelegateAdapterItem()

object Util {
    fun makePersonList(list: List<PersonDTO>): MutableList<DelegateAdapterItem> {
        val newList = mutableListOf<DelegateAdapterItem>()
        list.forEachIndexed { index, element ->
            if (element.dob != null) {
                newList.add(
                    PersonDOB(
                        id = index,
                        firstName = element.name.firstName,
                        lastName = element.name.lastName,
                        picture = element.picture.mediumPic,
                        phone = element.phone,
                        dob = element.dob.date
                    )
                )
            } else {
                newList.add(
                    PersonNoDOB(
                        id = index,
                        firstName = element.name.firstName,
                        lastName = element.name.lastName,
                        picture = element.picture.mediumPic,
                        phone = element.phone
                    )
                )
            }
        }
        return newList
    }
}