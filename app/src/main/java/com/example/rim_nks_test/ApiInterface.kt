package com.example.rim_nks_test

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/character")
    fun getMovie() : Call<Muvie2>
   // fun getMovies(@Query("api_key")sort:String) : Call<MovieX>  //Для ключа

    companion object {    //Такому объекту можно не указывать имя,
        // а к его компонентам обращаться через имя класса, в котором он находится.
        // Как правило объекты-компаньоны используются для объявления переменных и функций,
        // к которым требуется обращаться без создания экземпляра класса

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