package com.example.hw3_contacts.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Results(
    @Json(name = "results") val results: List<PersonDTO>
)

@JsonClass(generateAdapter = true)
data class PersonDTO(
    @Json(name = "name") val name: Name,
    @Json(name = "dob") val dob: Dob? = null,
    @Json(name = "cell") val phone: String,
    @Json(name = "picture") val picture: Picture
)

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "first") val firstName: String,
    @Json(name = "last") val lastName: String
)

@JsonClass(generateAdapter = true)
data class Dob(
    @Json(name = "date") val date: String,
    @Json(name = "age") val age: Int
)

@JsonClass(generateAdapter = true)
data class Picture(
    @Json(name = "large") val largePic: String,
    @Json(name = "medium") val mediumPic: String
)