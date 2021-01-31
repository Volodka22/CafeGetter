package com.example.myApp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyAdapter(private val users: Array<User>,val adapterOnClick : (Any) -> Unit) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val myData = mutableListOf(*users)


    inner class MyViewHolder internal constructor(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        internal val infoView: TextView = view.findViewById(R.id.txt)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.order, parent, false)
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val user = myData[position]

        holder.infoView.text = "Стол № ${user.numberTable}"

        holder.itemView.setOnClickListener{
            adapterOnClick(user)
        }

    }



    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = users.size
}