package com.example.myapplication

import android.R.array
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.w3c.dom.Text

class Single_Boss : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_single_boss)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var items_data=intent.getStringExtra("items")
        var location_data=intent.getStringExtra("location")
        var name_data=intent.getStringExtra("name")
        var strengths_data=intent.getStringExtra("strengths")
        var weakness_data=intent.getStringExtra("weakness")
        var image_data=intent.getStringExtra("image")

        var items=findViewById<TextView>(R.id.boss_items)
        var image=findViewById<ImageView>(R.id.boss_photo)
        var name=findViewById<TextView>(R.id.boss_intro)
        var location=findViewById<TextView>(R.id.boss_location)
        var strengths=findViewById<TextView>(R.id.boss_desc_strength)
        var weakness=findViewById<TextView>(R.id.boss_weakness_desc)


        items.text= items_data.toString()
        Picasso.get().load(image_data.toString()).into(image)
        name.text=name_data.toString()
        location.text=location_data.toString()
        strengths.text=strengths_data.toString()
        weakness.text=weakness_data.toString()

        var btn_add=findViewById<Button>(R.id.btn_add_boss)

        btn_add.setOnClickListener {
            var intent=Intent(this, New_Boss::class.java)
            startActivity(intent)
        }

    }
}

