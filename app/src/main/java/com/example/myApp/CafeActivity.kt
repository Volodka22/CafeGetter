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

class CafeActivity : AppCompatActivity() {



    private val users = mutableListOf<User>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    private fun doClick(user:User){
        val profile = Intent(this,Profile::class.java)
        profile.putExtra("user", user)
        startActivityForResult(profile,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 1 && data != null){
            if(data.getBooleanExtra("del",false)){
                val us:User = data.getSerializableExtra("user") as User
                if(data.getSerializableExtra("user") != null) {

                    Log.d("AAAA", us.numberTable.toString())
                }




                FirebaseDatabase.getInstance().reference.
                    child(intent.getStringExtra("cafeName")).
                    child("заказы").child(us.key).removeValue()


                users.removeAt(users.indexOfFirst {
                    it.key == us.key
                })


                update()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun update() {

        runOnUiThread {

            viewManager = LinearLayoutManager(this)
            viewAdapter = MyAdapter(users.toTypedArray()) { item -> doClick(item as User)}

            recyclerView = findViewById<RecyclerView>(R.id.list).apply {

                setHasFixedSize(false)

                layoutManager = viewManager

                adapter = viewAdapter

            }


        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe)


        val myRef =
            FirebaseDatabase.getInstance().reference.
                child(intent.getStringExtra("cafeName")).child("заказы")


        myRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val user = dataSnapshot.getValue<User>(User::class.java)!!
                user.key = dataSnapshot.key
                users.add(user)
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
