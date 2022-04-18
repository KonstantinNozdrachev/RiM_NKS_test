package com.example.rim_nks_test

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.rim_nks_test.Result as Result

class MainActivity2 : AppCompatActivity() {
    private lateinit var name: TextView
    private lateinit var rassa: TextView
    private lateinit var pol: TextView
    private lateinit var status: TextView
    private lateinit var locaciya: TextView
    private lateinit var seria: TextView
    private lateinit var ava: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val id = intent.getIntExtra("id",0)

        name=findViewById(R.id.name)
        rassa=findViewById(R.id.rassa)
        pol=findViewById(R.id.pol)
        status=findViewById(R.id.status)
        locaciya=findViewById(R.id.locaciya)
        seria=findViewById(R.id.seria)
        ava=findViewById(R.id.ava)


        val apiInterface = ApiInterface.create().getDetali(id)
        apiInterface.enqueue( object: Callback<Result> {
            override fun onResponse(call: Call<Result>?, response: Response<Result>?) {

                name.text = response?.body()?.name
                rassa.text = response?.body()?.species
                pol.text = response?.body()?.gender
                status.text = response?.body()?.status
                locaciya.text = response?.body()?.location?.name
                seria.text = response?.body()?.episode?.size.toString()

                Picasso.get()
                    .load("https://rickandmortyapi.com/api/character/avatar/$id.jpeg")
                    .into(ava)
            }
            override fun onFailure(call:Call<Result>?, t:Throwable?){


            }

        })



    }
}




