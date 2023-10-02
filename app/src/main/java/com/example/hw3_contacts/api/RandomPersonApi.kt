package com.example.hw3_contacts.api

import com.example.hw3_contacts.data.Results
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://randomuser.me/api/"


object RetrofitServices {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val randomPersonApi: RandomPersonApi = retrofit.create(RandomPersonApi::class.java)
}
interface RandomPersonApi {
    @GET(".")
    suspend fun getPersonsWithDOB(
        @Query("inc") inc: String = "picture,name,cell,dob",
        @Query("results") results: Int = 50
    ): Response<Results>
    @GET(".")
    suspend fun getPersonsNoDOB(
        @Query("inc") inc: String = "picture,name,cell",
        @Query("results") results: Int = 50
    ): Response<Results>
}