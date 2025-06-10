package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class Sign_up : AppCompatActivity() {
    private lateinit var background: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var background=findViewById<ImageView>(R.id.background_sign_up)
        var username=findViewById<EditText>(R.id.new_username)
        var password=findViewById<EditText>(R.id.new_password)
        var email=findViewById<EditText>(R.id.new_username)
        var btn_sign_up=findViewById<Button>(R.id.btn_sign_up)

        Picasso.get()
                .load("https://i.pinimg.com/736x/d1/86/2a/d1862af6ad6ba39243b3abe13111ec12.jpg")
                .into(background)


        btn_sign_up.setOnClickListener {
            User_Manager.addUser(this,username.text.toString(),password.text.toString(),email.text.toString()) {
                var intent=Intent(this,Home::class.java)
                startActivity(intent)
            }

        }



    }
}