package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class Single_weapon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_single_weapon)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var name=findViewById<TextView>(R.id.weapon_show_name)
        var image=findViewById<ImageView>(R.id.weapon_show_img)
        var owner=findViewById<TextView>(R.id.weapon_show_owner)
        var scales=findViewById<TextView>(R.id.weapon_show_scale)
        var skill=findViewById<TextView>(R.id.weapon_show_skill)
        var btn=findViewById<Button>(R.id.btn_add_weapon1)

        var owner_data=intent.getStringExtra("owner")
        var scale_data=intent.getStringExtra("scalability")
        var name_data=intent.getStringExtra("name")
        var skill_data=intent.getStringExtra("skill")
        var image_data=intent.getStringExtra("image")

        //owner.text= owner_data.toString()
        Picasso.get().load(image_data.toString()).into(image)
        name.text=name_data.toString()
        scales.text=scale_data.toString()
        skill.text=skill_data.toString()



        btn.setOnClickListener {
            var intent=Intent(this, new_weapon::class.java)
            startActivity(intent)
        }


    }
}