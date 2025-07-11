package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Boss_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_boss_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recycler=findViewById<RecyclerView>(R.id.boss_recycler)

        if(Boss_Manager.bosses.isEmpty())
        {

            Boss_Manager.getBosses(this) {
                val layoutmanager= LinearLayoutManager(this)
                recycler.layoutManager=layoutmanager

                val item_adapter= Boss_Adapter(this, Boss_Manager.bosses)
                recycler.adapter=item_adapter
            }

        }
    }
}