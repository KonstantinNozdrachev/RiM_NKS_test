package com.example.rim_nks_test

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/character")
    fun getMovie() : Call<Muvie2>


    @GET("api/character/{id}")
    fun getDetali(@Path("id")id:Int) : Call<Result>

    companion object {

        var BASE_URL = " https://rickandmortyapi.com/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}