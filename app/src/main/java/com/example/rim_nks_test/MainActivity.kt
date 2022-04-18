package com.example.rim_nks_test

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(this,2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.ic_launcher_background, "Item " + i)) //Картинка
        }



        val apiInterface = ApiInterface.create().getMovie()
       // val apiInterface = ApiInterface.create().getMovies(R.string.api_key.toString())

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<Muvie2>, CustomAdapter.ItemClickListener {          // слушатель ответа с сервера
            override fun onResponse(call: Call<Muvie2>?, response: Response<Muvie2>?) {
                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapter(response?.body()?.results,this)



                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter
//                if(response?.body() != null)                                     //Для монипуляций со списком
 //                   recyclerAdapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<Muvie2>?, t: Throwable?) {

            }

            override fun onItemClick(id: Int) {
                val intent = Intent (this@MainActivity,MainActivity2::class.java)
                intent.putExtra("id",id)
                startActivity(intent)

            }
        })

    }
}
