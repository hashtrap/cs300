package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class New_Boss : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_boss)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btn_add=findViewById<Button>(R.id.btn_add_boss)
        var items=findViewById<EditText>(R.id.new_items)
        var location=findViewById<EditText>(R.id.new_location)
        var name=findViewById<EditText>(R.id.name_boss)
        var strengths=findViewById<EditText>(R.id.new_skill)
        var weakness=findViewById<EditText>(R.id.new_weapon_image)
        var image=findViewById<EditText>(R.id.new_image)

        btn_add.setOnClickListener {
            Boss_Manager.addBoss(this,items.text.toString(),location.text.toString(),name.text.toString(),strengths.text.toString(),weakness.text.toString(),image.text.toString())
            {
                var intent=Intent(this, Home::class.java)
                startActivity(intent)
            }
        }
    }
}