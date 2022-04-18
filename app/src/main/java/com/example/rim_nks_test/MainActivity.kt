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




        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)


        recyclerview.layoutManager = GridLayoutManager(this,2)


        val data = ArrayList<ItemsViewModel>()


        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.ic_launcher_background, "Item " + i))
        }



        val apiInterface = ApiInterface.create().getMovie()



        apiInterface.enqueue(object : Callback<Muvie2>, CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<Muvie2>?, response: Response<Muvie2>?) {

                val adapter = CustomAdapter(response?.body()?.results,this)




                recyclerview.adapter = adapter
//
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
