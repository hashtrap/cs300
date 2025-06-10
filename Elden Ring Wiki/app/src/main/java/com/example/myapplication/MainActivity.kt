package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.User_Manager.Companion.users
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var background: ImageView
    private lateinit var  btn_log_in: Button
    private lateinit var  btn_sign_up: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        background=findViewById(R.id.background)
        btn_log_in=findViewById(R.id.Log_in)
        btn_sign_up=findViewById(R.id.Sign_up)

        Picasso.get().
                load("https://image.api.playstation.com/vulcan/img/rnd/202111/0506/hcFeWRVGHYK72uOw6Mn6f4Ms.jpg").
                into(background)

        btn_log_in.setOnClickListener {


            var intent=Intent(this, Log_in::class.java)
            startActivity(intent)
        }

        btn_sign_up.setOnClickListener {
            var intent=Intent(this, Sign_up::class.java)
            startActivity(intent)
        }
    }
}