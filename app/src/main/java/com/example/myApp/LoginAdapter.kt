package com.example.myApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class LoginAdapter(private val cafes: MutableList<String>,val adapterOnClick : (Any) -> Unit) :
    androidx.recyclerview.widget.RecyclerView.Adapter<LoginAdapter.MyViewHolder>() {


    inner class MyViewHolder internal constructor(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        internal val infoView: TextView = view.findViewById(R.id.txt)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.order, parent, false)
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val cafe = cafes[position]

        holder.infoView.text = cafe

        holder.itemView.setOnClickListener{
            adapterOnClick(cafe)
        }

    }



    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = cafes.size
}