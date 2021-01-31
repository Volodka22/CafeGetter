package com.example.myApp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val user = intent.getSerializableExtra("user") as User

        order.movementMethod = ScrollingMovementMethod()

        number.text = "Стол № ${user.numberTable}"
        order.text = ""
        user.array.forEach {
            order.append( "${it.name} : ${it.count}\n")
        }

        button.setOnClickListener {
            val int = Intent()
            int.putExtra("user",user)
            int.putExtra("del",true)
            setResult(Activity.RESULT_OK,int)
            finish()
        }

    }
}