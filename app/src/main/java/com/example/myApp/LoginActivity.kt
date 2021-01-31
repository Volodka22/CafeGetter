package com.example.myApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    private val cafes = mutableListOf<String>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    private fun doClick(cafe:String){
        val profile = Intent(this,CafeActivity::class.java)
        profile.putExtra("cafeName", cafe)
        startActivity(profile)
    }


    private fun update() {

        runOnUiThread {

            viewManager = LinearLayoutManager(this)
            viewAdapter = LoginAdapter(cafes) { item -> doClick(item as String)}

            recyclerView = findViewById<RecyclerView>(R.id.list).apply {

                setHasFixedSize(false)

                layoutManager = viewManager

                adapter = viewAdapter

            }


        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val myRef = FirebaseDatabase.getInstance().reference.
            child("Заведения")


        myRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                cafes.add(dataSnapshot.key!!)
                update()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, p1: String?) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })


    }
}
