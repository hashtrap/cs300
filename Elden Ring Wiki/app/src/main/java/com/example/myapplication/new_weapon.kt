package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class new_weapon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_weapon)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var btn_add=findViewById<Button>(R.id.btn_add_weapon2)
        var name=findViewById<EditText>(R.id.name_weapon)
        var owner=findViewById<EditText>(R.id.new_owner)
        var scale=findViewById<EditText>(R.id.new_scale)
        var skill=findViewById<EditText>(R.id.new_skill)
        var image=findViewById<EditText>(R.id.new_weapon_image)

        btn_add.setOnClickListener {
            Weapon_Manager.addWeapon(this,name.text.toString(),owner.text.toString(),scale.text.toString()
                ,skill.text.toString(),image.text.toString()) {
                var intent= Intent(this, Home::class.java)
                startActivity(intent)

            }
        }

    }
}