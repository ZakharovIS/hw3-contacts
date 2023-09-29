package com.example.hw3_contacts.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://randomuser.me/api/"


object RetrofitServices {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val randomPersonApi: RandomPersonApi = retrofit.create(RandomPersonApi::class.java)
}
interface RandomPersonApi {
    @GET
    suspend fun getPersonsWithDOB(
        @Query("inc") inc: String = "picture,name,cell,dob",
        @Query("results") results: Int = 50
    )
    @GET
    suspend fun getPersonsNoDOB(
        @Query("inc") inc: String = "picture,name,cell",
        @Query("results") results: Int = 50
    )
}