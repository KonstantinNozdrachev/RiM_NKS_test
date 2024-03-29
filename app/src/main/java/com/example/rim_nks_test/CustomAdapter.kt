package com.example.rim_nks_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomAdapter(private val mList: List<Result>?,val mItemClickListener:ItemClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick (position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_shabl, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList?.get(position)


        Picasso.get().load("https://rickandmortyapi.com/api/character/avatar/${mList?.get(position)?.id}.jpeg").into(holder.imageView);



        holder.main_rassa.text = ItemsViewModel?.species
        holder.main_pol.text = ItemsViewModel?.gender

        holder.textView.text = ItemsViewModel?.name

    }


    override fun getItemCount(): Int {
        return mList!!.size
    }


    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)

        val main_rassa:TextView = itemView.findViewById(R.id.main_rassa)
        val main_pol:TextView = itemView.findViewById(R.id.main_pol)

        init {
           ItemView.setOnClickListener{
               mList?.get(position)?.id?.let {it -> mItemClickListener.onItemClick(it)}
           }
        }
    }
}
